package com.bookstore.bookstore.controllers;

import com.bookstore.bookstore.models.*;
import com.bookstore.bookstore.repositories.OrderRepository;
import com.bookstore.bookstore.security.PasswordResetToken;
import com.bookstore.bookstore.security.Role;
import com.bookstore.bookstore.security.UserRole;
import com.bookstore.bookstore.services.*;
import com.bookstore.bookstore.utility.MailConstructor;
import com.bookstore.bookstore.utility.SecurityUtility;

import com.bookstore.bookstore.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
//import sun.java2d.jules.IdleTileCache;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;


@Controller
public class IndexController {

    private UserService userService;
    private UserSecurityService userSecurityService;
    private JavaMailSender mailSender;
    private MailConstructor mailConstructor;
    private BookService bookService;
    private UserPaymentService userPaymentService;
    private UserShippingService userShippingService;
    private OrderService orderService;
    private OrderRepository orderRepository;

    @Autowired
    public IndexController(UserService userService, UserSecurityService userSecurityService,
                           JavaMailSender mailSender,
                           MailConstructor mailConstructor,
                           BookService bookService,
                           UserPaymentService userPaymentService,
                           UserShippingService userShippingService
    ) {
        this.userService = userService;
        this.userSecurityService = userSecurityService;
        this.mailSender = mailSender;
        this.mailConstructor = mailConstructor;
        this.bookService = bookService;
        this.userPaymentService = userPaymentService;
        this.userShippingService = userShippingService;
    }

    @RequestMapping("/")
    public String index(@RequestParam(value = "sortColumn", required = false) String sort,
                        @RequestParam(value = "topseller", required = false) Boolean topseller,
                        Model model, Principal principal) {
        BookshelfForm bf = new BookshelfForm(sort);
        bf.setTopseller(topseller);
        model.addAttribute("formobject", bf);
        List<String> sortColumns = Arrays.asList(new String[]{"title", "author", "date", "rating asc", "rating desc", "price"});
        model.addAttribute("sortColumns", sortColumns);
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);

        return "MyAccount";
    }

    @RequestMapping("/bookshelf")
    public String bookshelf(@RequestParam(value = "sortColumn", required = false) String sort,
                            @RequestParam(value = "topseller", required = false) Boolean topseller,
                            Model model, Principal principal) {

        List<Book> bookList = bookService.findAll();
        List<String> categoryList = bookService.findDistinctCategoryBy();
        List<String> genreList = bookService.findDistinctGenreBy();

        model.addAttribute("bookList", bookList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("genreList", genreList);

        BookshelfForm bf = new BookshelfForm(sort);
        bf.setTopseller(topseller);
        bf.setPageSize(10);
        model.addAttribute("formobject", bf);
        List<String> sortColumns = Arrays.asList(new String[]{"title", "author", "date", "rating asc", "rating desc", "price"});
        model.addAttribute("sortColumns", sortColumns);
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        return "bookshelf";
    }

    @RequestMapping(value = "/formbookshelf")
    public String bookshelf(@RequestParam(value = "topseller", required = false) Boolean topseller,
                            @RequestParam(value = "sortColumn", required = false) String sort,
                            @RequestParam(value = "fiveStars", required = false) Boolean fiveStars,
                            @RequestParam(value = "fourStars", required = false) Boolean fourStars,
                            @RequestParam(value = "threeStars", required = false) Boolean threeStars,
                            @RequestParam(value = "twoStars", required = false) Boolean twoStars,
                            @RequestParam(value = "oneStars", required = false) Boolean oneStars,
                            @RequestParam(value = "minPrice", required = false) double minPrice,
                            @RequestParam(value = "maxPrice", required = false) double maxPrice,
                            @RequestParam(value = "category", required = false) String category,
                            @RequestParam(value = "genre", required = false) String genre,
                            @RequestParam(value = "page", required = false) int page,
                            @RequestParam(value = "pageSize", required = false) int pageSize,
                            @RequestParam(value = "prev", required = false) String prev,
                            @RequestParam(value = "next", required = false) String next,
                            Model model, Principal principal) {

        List<Book> bookList = bookService.findAll(); //Initializing to full list
        BookshelfForm bf = new BookshelfForm(sort);
        bf.setTopseller(topseller);

        /*   Sorting books based on user selection */

        if (sort == null || "".equals(sort) || "title".equalsIgnoreCase(sort)) {
            sort = "title";
            if (topseller == null || !topseller) {
                bookList = bookService.findAllByOrderByTitleAsc();
            } else {
                bookList = bookService.findByTopsellerOrderByTitleAsc(topseller);
            }

        }  else if ("author".equalsIgnoreCase(sort)) {
            if (topseller == null || !topseller) {
                bookList = bookService.findAllByOrderByAuthorAsc();
            } else {
                bookList = bookService.findByTopsellerOrderByAuthorAsc(topseller);
            }
        } else if ("date".equalsIgnoreCase(sort)){
            if (topseller == null || !topseller) {
                bookList = bookService.findAllByOrderByPublicationdate();
            } else {
                bookList = bookService.findByTopsellerOrderByPublicationdate(topseller);
            }

        } else if ("rating asc".equalsIgnoreCase(sort)){
            if (topseller == null || !topseller) {
                bookList = bookService.findAllByOrderByRatingAsc();
            } else {
                bookList = bookService.findByTopsellerOrderByRatingAsc(topseller);
            }

        } else if ("rating desc".equalsIgnoreCase(sort)){
            if (topseller == null || !topseller) {
                bookList = bookService.findAllByOrderByRatingDesc();
            } else {
                bookList = bookService.findByTopsellerOrderByRatingDesc(topseller);
            }

        } else if ("price".equalsIgnoreCase(sort)){
            if (topseller == null || !topseller) {
                bookList = bookService.findAllByOrderByPriceAsc();
            } else {
                bookList = bookService.findByTopsellerOrderByPriceAsc(topseller);
            }

        } else {
            model.addAttribute("emptyList", Boolean.TRUE);
        }

        /*   Filtering books based on user selection */

        /*   RATING FILTER */

        if(!(fiveStars==null && fourStars==null && threeStars==null && twoStars==null && oneStars==null)){
            if(fiveStars==null){
                bookList.removeIf(book -> (book.getRating()==5.0));
            }
            else{
                bf.setFiveStars(Boolean.TRUE);
            }
            if(fourStars==null){
                bookList.removeIf(book -> (book.getRating()>=4.0 && book.getRating()<5.0));
            }
            else{
                bf.setFourStars(Boolean.TRUE);
            }
            if(threeStars==null){
                bookList.removeIf(book -> (book.getRating()>=3.0 && book.getRating()<4.0));
            }
            else{
                bf.setThreeStars(Boolean.TRUE);
            }
            if(twoStars==null){
                bookList.removeIf(book -> (book.getRating()>=2.0 && book.getRating()<3.0));
            }
            else{
                bf.setTwoStars(Boolean.TRUE);
            }
            if(oneStars==null){
                bookList.removeIf(book -> (book.getRating()>=1.0 && book.getRating()<2.0));
            }
            else{
                bf.setOneStars(Boolean.TRUE);
            }
            bookList.removeIf(book -> (book.getRating()<1.0));

        }
        else{
            bf.setFiveStars(null);
            bf.setFourStars(null);
            bf.setThreeStars(null);
            bf.setTwoStars(null);
            bf.setOneStars(null);
        }

        /*   PRICE FILTER */

        if(minPrice>0.0){
            bookList.removeIf(book -> (book.getOurPrice()<minPrice));
            bf.setMinPrice(minPrice);
        }

        if(maxPrice>0.0){
            bookList.removeIf(book -> (book.getOurPrice()>maxPrice));
            bf.setMaxPrice(maxPrice);
        }

        /* CATEGORY FILTER */

        if(!category.equalsIgnoreCase("nochoice")) {
            bookList.removeIf(book -> !(book.getCategory().equalsIgnoreCase(category)));
            bf.setCategory(category);

        }
        /* GENRE FILTER */
        if(!genre.equalsIgnoreCase("nochoice")) {
           bookList.removeIf(book -> !(book.getGenre().equalsIgnoreCase(genre)));
           bf.setGenre(genre);
        }

        List<String> categoryList = bookService.findDistinctCategoryBy();
        List<String> genreList = bookService.findDistinctGenreBy();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("genreList", genreList);
        List<String> sortColumns = Arrays.asList(new String[]{"title", "author", "date", "rating asc", "rating desc", "price"});
        model.addAttribute("sortColumns", sortColumns);
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        PagedListHolder<Book> pagedListHolder = new PagedListHolder<>(bookList);
        pagedListHolder.setPageSize(pageSize);
        pagedListHolder.setPage(page);
        if(prev != null){
            if(!pagedListHolder.isFirstPage()){
                page--;
            }
        }
        else if (next != null){
            if(!pagedListHolder.isLastPage()){
                page++;
            }
        }
        else {
            page = 0;
        }

        pagedListHolder.setPage(page);
        bf.setPageSize(pageSize);
        bf.setPage(page);

        model.addAttribute("formobject", bf);
        model.addAttribute("bookList", pagedListHolder.getPageList());
        model.addAttribute("booksAuthor", false);
        return "bookshelf";
    }


    @RequestMapping("/bookDetail")
    public String bookDetail(
            @PathParam("id") Long id, Model model, Principal principal
    ) {

        boolean allowToReview = false;

        Book book = bookService.findBookById(id);

        model.addAttribute("book", book);

        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);

        List<Order> orders = user.getOrderList();

            for (Order order : orders) {
                if(order.getUser().getId() == user.getId()){
                    for (CartItem cartItem : order.getCartItemList()) {
                        if(cartItem.getBook().getId() == book.getId())
                            allowToReview = true;
                    }
                }
            }

        }

        model.addAttribute("allowToReview", allowToReview);

        List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        model.addAttribute("averageRating", bookService.getAverageRating(id));

        model.addAttribute("bookReviewsList", book.getReviewsList());

        model.addAttribute("numberOfReviews", bookService.getNumberOfReviews(id));

        if (bookService.getNumberOfReviews(id) == 0) {
            model.addAttribute("NoReviews", true);
        } else {
            model.addAttribute("Reviews", true);
        }

        return "bookDetail";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(
            HttpServletRequest request,
            @ModelAttribute("email") String email,
            Model model
    ) {

        model.addAttribute("classActiveForgetPassword", true);

        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("emailNotExist", true);
            return "MyAccount";
        }

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        userService.save(user);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        SimpleMailMessage newEmail = mailConstructor.constuctResetTokenEmail(appUrl, request.getLocale(), token, user, password);

        mailSender.send(newEmail);

        model.addAttribute("forgetPasswordEmailSent", "true");


        return "MyAccount";
    }

    @RequestMapping("/shoppingCart")
    public String shoppingCart(){
        return "shoppingCart";
    }

    @PostMapping("/newUser")
    public String newUserPost(
            HttpServletRequest request,
            @ModelAttribute("email") String userEmail,
            @ModelAttribute("Username") String userName,
            Model model) throws Exception {
        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", userEmail);
        model.addAttribute("username", userName);


        if (userService.findByEmail(userEmail) != null) {
            model.addAttribute("emailExists", true);
            return "MyAccount";
        }

        if (userService.findByUsername(userName) != null) {
            model.addAttribute("usernameExists", true);
            return "MyAccount";
        }


        User user = new User();
        user.setUsername(userName);
        user.setEmail(userEmail);
        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

        String token = UUID.randomUUID().toString();

        userService.createPasswordRestTokenForUser(user, token);

        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        SimpleMailMessage email = mailConstructor.constuctResetTokenEmail(appUrl, request.getLocale(), token, user, password);
        mailSender.send(email);

        model.addAttribute("emailSent", true);

        return "MyAccount";
    }

    @RequestMapping("/newUser")
    public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
        PasswordResetToken passwordResetToken = userService.getPasswordResetToken(token);

        if (passwordResetToken == null) {
            model.addAttribute("message", "Invalid token");

            return "redirect:/badRequest";
        }

        User user = passwordResetToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("user", user);

        model.addAttribute("classActiveEdit", true);

        return "MyProfile";
    }

    @RequestMapping("/MyProfile")
    public String myProfile(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        /*model.addAttribute("orderList", user.getOrderList());*/

        UserShipping userShipping = new UserShipping();
        model.addAttribute("userShipping", userShipping);

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("listOfShippingAddresses", true);

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);
        model.addAttribute("classActiveEdit", true);

        return "MyProfile";
    }

    @RequestMapping("/listOfCreditCards")
    public String listOfCreditCards(
            Model model, Principal principal, HttpServletRequest request
    ) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        /*model.addAttribute("orderList", user.orderList());*/

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        return "MyProfile";
    }

    @RequestMapping("/listOfShippingAddresses")
    public String listOfShippingAddresses(
            Model model, Principal principal, HttpServletRequest request
    ) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        /*model.addAttribute("orderList", user.orderList());*/

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfShippingAddresses", true);

        return "MyProfile";
    }

    @RequestMapping("/addNewCreditCard")
    public String addNewCreditCard(
            Model model, Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        model.addAttribute("addNewCreditCard", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfShippingAddresses", true);

        UserBilling userBilling = new UserBilling();
        UserPayment userPayment = new UserPayment();


        model.addAttribute("userBilling", userBilling);
        model.addAttribute("userPayment", userPayment);

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        /*model.addAttribute("orderList", user.orderList());*/

        return "MyProfile";
    }

    @RequestMapping(value = "/addNewCreditCard", method = RequestMethod.POST)
    public String addNewCreditCard(
            @ModelAttribute("userPayment") UserPayment userPayment,
            @ModelAttribute("userBilling") UserBilling userBilling,
            Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.updateUserBilling(userBilling, userPayment, user);

        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        return "MyProfile";
    }


    @RequestMapping("/updateCreditCard")
    public String updateCreditCard(
            @ModelAttribute("id") Long creditCardId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        UserPayment userPayment = userPaymentService.findById(creditCardId);

        if (!user.getId().equals(userPayment.getUser().getId())) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);
            UserBilling userBilling = userPayment.getUserBilling();
            model.addAttribute("userPayment", userPayment);
            model.addAttribute("userBilling", userBilling);

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            model.addAttribute("addNewCreditCard", true);
            model.addAttribute("classActiveBilling", true);
            model.addAttribute("listOfShippingAddresses", true);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());

            return "MyProfile";
        }
    }

    @RequestMapping("/updateUserShipping")
    public String updateUserShipping(
            @ModelAttribute("id") Long shippingAddressId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(shippingAddressId);

        if (user.getId() != userShipping.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);

            model.addAttribute("userShipping", userShipping);

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            model.addAttribute("addNewShippingAddress", true);
            model.addAttribute("classActiveShipping", true);
            model.addAttribute("listOfCreditCards", true);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());

            return "MyProfile";
        }
    }


    @RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
    public String updateUserInfo(
            @ModelAttribute("user") User user,
            @ModelAttribute("newPassword") String newPassword,
            Model model
    ) throws Exception {
        User currentUser = userService.findById(user.getId());

        if(currentUser == null) {
            throw new Exception ("User not found");
        }

        /*check email already exists*/
        if (userService.findByEmail(user.getEmail())!=null) {
            if(userService.findByEmail(user.getEmail()).getId() != currentUser.getId()) {
                model.addAttribute("emailExists", true);
                return "MyProfile";
            }
        }

        /*check username already exists*/
        if (userService.findByUsername(user.getUsername())!=null) {
            if(userService.findByUsername(user.getUsername()).getId() != currentUser.getId()) {
                model.addAttribute("usernameExists", true);
                return "MyProfile";
            }
        }

//		update password
        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")){
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            // String dbPassword = currentUser.getPassword();
            // if(passwordEncoder.matches(user.getPassword(), dbPassword)){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            // } else {
            //  model.addAttribute("incorrectPassword", true);

            //   return "MyProfile";
            // }
        }

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());

        userService.save(currentUser);

        model.addAttribute("updateSuccess", true);
        model.addAttribute("user", currentUser);
        model.addAttribute("classActiveEdit", true);

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("listOfCreditCards", true);

        UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("orderList", user.getOrderList());

        return "MyProfile";
    }



    @RequestMapping(value = "/setDefaultPayment", method = RequestMethod.POST)
    public String setDefaultPayment(
            @ModelAttribute("defaultUserPaymentId") Long defaultPaymentId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.setUserDefaultPayment(defaultPaymentId, user);

        model.addAttribute("user", user);
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());

        return "MyProfile";
    }


    @RequestMapping(value = "/setDefaultShippingAddress", method = RequestMethod.POST)
    public String setDefaultShippingAddress(
            @ModelAttribute("defaultShippingAddressId") Long defaultShippingId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.setUserDefaultShipping(defaultShippingId, user);

        model.addAttribute("user", user);
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfShippingAddresses", true);

        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());

        return "MyProfile";
    }

    @RequestMapping("/removeCreditCard")
    public String removeCreditCard(
            @ModelAttribute("id") Long creditCardId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        UserPayment userPayment = userPaymentService.findById(creditCardId);

        if (!user.getId().equals(userPayment.getUser().getId())) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);
            userPaymentService.removeById(creditCardId);

            model.addAttribute("listOfCreditCards", true);
            model.addAttribute("classActiveBilling", true);
            model.addAttribute("listOfShippingAddresses", true);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());

            return "MyProfile";
        }
    }

    @RequestMapping("/removeUserShipping")
    public String removeUserShipping(
            @ModelAttribute("id") Long userShippingId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(userShippingId);

        if (user.getId() != userShipping.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);

            userShippingService.removeById(userShippingId);

            model.addAttribute("listOfShippingAddresses", true);
            model.addAttribute("classActiveShipping", true);

            model.addAttribute("userPaymentList", user.getUserPaymentList());
            model.addAttribute("userShippingList", user.getUserShippingList());

            return "MyProfile";
        }
    }

    @RequestMapping("/addNewShippingAddress")
    public String addNewShippingAddress(
            Model model, Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        model.addAttribute("addNewShippingAddress", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfCreditCards", true);

        UserShipping userShipping = new UserShipping();

        model.addAttribute("userShipping", userShipping);

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        /*model.addAttribute("orderList", user.orderList());*/

        return "MyProfile";
    }

    @RequestMapping(value = "/addNewShippingAddress", method = RequestMethod.POST)
    public String addNewShippingAddressPost(
            @ModelAttribute("userShipping") UserShipping userShipping,
            Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.updateUserShipping(userShipping, user);

        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfCreditCards", true);

        return "MyProfile";
    }

    @RequestMapping("/searchTitle")
    public String searchTitle(@RequestParam("title") String title,
                              @RequestParam(value = "topseller", required = false) Boolean topseller,
                              @RequestParam(value = "sortColumn", required = false) String sort,
                              Model model, Principal principal) {

        List<Book> bookList;

        if(title.equalsIgnoreCase("")){
            bookList = bookService.findAll();
        } else{
            bookList = bookService.findByTitle(title);
        }

        List<String> categoryList = bookService.findDistinctCategoryBy();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("bookList", bookList);
        model.addAttribute("booksAuthor", false);
        BookshelfForm bf = new BookshelfForm(sort);
        bf.setTopseller(topseller);
        model.addAttribute("formobject", bf);
        List<String> sortColumns = Arrays.asList(new String[]{"title", "author", "date", "rating asc", "rating desc", "price"});
        model.addAttribute("sortColumns", sortColumns);
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }


        return "bookshelf";
    }

    @RequestMapping("/bookauthorList")
    public String searchAuthor(@RequestParam(value = "author") String author,
                              @RequestParam(value = "topseller", required = false) Boolean topseller,
                              @RequestParam(value = "sortColumn", required = false) String sort,
                              Model model, Principal principal) {

        List<Book> bookList = bookService.findByAuthor(author);
        List<String> categoryList = bookService.findDistinctCategoryBy();


        model.addAttribute("booksAuthor", true);
        model.addAttribute("authorName", author);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("bookList", bookList);
        BookshelfForm bf = new BookshelfForm(sort);
        bf.setTopseller(topseller);
        model.addAttribute("formobject", bf);
        List<String> sortColumns = Arrays.asList(new String[]{"title", "author", "date", "rating asc", "rating desc", "price"});
        model.addAttribute("sortColumns", sortColumns);
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }


        return "bookshelf";

    }

}