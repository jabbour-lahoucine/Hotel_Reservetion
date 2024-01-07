<%@ page import="dao.RoomDao" %>
<%@ page import="dao.EmployeeDao" %>
<%@ page import="dao.ClientDao" %>
<%@ page import="entities.RoomCategory" %>
<%@ page import="dao.RoomCategoryDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gtwo.hotelreservation.ReservationServlet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <!-- Favicon -->
  <link href="img/favicon.ico" rel="icon">

  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">

  <!-- Icon Font Stylesheet -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">


  <!-- Customized Bootstrap Stylesheet -->
  <link href="css/bootstrap.min.css" rel="stylesheet">

  <!-- Template Stylesheet -->
  <link href="css/style.css" rel="stylesheet">

  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

  <title>Hotel Project</title>
</head>
<body>
<div class="container-xxl bg-white p-0">
  <!-- Header Start -->
  <div class="container-fluid bg-dark px-0">
    <div class="row gx-0">
      <div class="col-lg-3 bg-dark d-none d-lg-block">
        <a href="index.jsp" class="navbar-brand w-100 h-100 m-0 p-0 d-flex align-items-center justify-content-center">
          <h1 class="m-0 text-primary text-uppercase">Hotelier</h1>
        </a>
      </div>
      <div class="col-lg-9">
        <div class="row gx-0 bg-white d-none d-lg-flex">
          <div class="col-lg-7 px-5 text-start">
            <div class="h-100 d-inline-flex align-items-center py-2 me-4">
            </div>
            <div class="h-100 d-inline-flex align-items-center py-2">
            </div>
          </div>
          <div class="col-lg-5 px-5 text-end">
            <div class="d-inline-flex align-items-center py-2">
            </div>
          </div>
        </div>
        <nav class="navbar navbar-expand-lg bg-dark navbar-dark p-3 p-lg-0">
          <a href="index.jsp" class="navbar-brand d-block d-lg-none">
            <h1 class="m-0 text-primary text-uppercase">Hotelier</h1>
          </a>
          <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
            <div class="navbar-nav mr-auto py-0">
            </div>
            <a href="https://htmlcodex.com/hotel-html-template-pro" class="btn btn-primary rounded-0 py-4 px-md-5 d-none d-lg-block">Administration<i class="fa fa-arrow-right ms-3"></i></a>
          </div>
        </nav>
      </div>
    </div>
  </div>
  <!-- Header End -->


  <!-- Carousel Start -->
  <div class="container-fluid p-0 mb-5">
    <div id="header-carousel" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img class="w-100" src="img/carousel-1.jpg" alt="Image">
          <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
            <div class="p-3" style="max-width: 700px;">
              <h6 class="section-title text-white text-uppercase mb-3 animated slideInDown">Luxury Living</h6>
              <h1 class="display-3 text-white mb-4 animated slideInDown">Discover A Brand Luxurious Hotel</h1>
            </div>
          </div>
        </div>
    </div>
  </div>
  <!-- Carousel End -->

  <!-- check availability  Start -->
  <div class="container-fluid booking pb-5 wow fadeIn" data-wow-delay="0.1s">
    <div class="container">
      <div class="bg-white shadow" style="padding: 35px;">
        <div class="row g-2">
          <div class="col-md-20">
            <form action="${pageContext.request.contextPath}/CheckAvailabilityServlet" method="get">
              <div class="row g-1">
                <div class="row g-1">
                  <div class="col-md-3">
                    <div class="date" id="date1" data-target-input="nearest">
                      <input type="date" class="form-control" name="checkInDate"
                             placeholder="Check in" data-target="#date1" data-toggle="datetimepicker"/>
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div class="date" id="date2" data-target-input="nearest">
                      <input type="date" class="form-control" name="checkOutDate"
                             placeholder="Check out" data-target="#date2" data-toggle="datetimepicker"/>
                    </div>
                  </div>
                  <div class="col-md-3">
                    <select class="form-select" name="categoryId">
                      <option selected>Room category</option>
                      <option value="1">King</option>
                      <option value="2">Single</option>
                      <option value="3">double</option>
                      <option value="4">Suite</option>
                    </select>
                  </div>
                  <div class="col-md-3">
                    <button type="submit" class="btn btn-primary w-100">Check availability</button>
                  </div>
                </div>
              </div>
            </form>
            <div id="messageContainer"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
    <div id="availabilityMessage"></div>
  <!-- About Start -->
  <div class="container-xxl py-5">
    <div class="container">
      <div class="row g-5 align-items-center">
        <div class="col-lg-6">
          <h6 class="section-title text-start text-primary text-uppercase">About Us</h6>
          <h1 class="mb-4">Welcome to <span class="text-primary text-uppercase">Hotelier</span></h1>
          <p class="mb-4">Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam et eos. Clita erat ipsum et lorem et sit, sed stet lorem sit clita duo justo magna dolore erat amet</p>
          <%
            RoomDao daoRoom = new RoomDao();
            EmployeeDao employeeDao = new EmployeeDao();
            ClientDao clientDao = new ClientDao();
//                        int numberRoom = daoRoom.getNumberRoom();
          %>
          <div class="row g-3 pb-4">
            <div class="col-sm-4 wow fadeIn" data-wow-delay="0.1s">
              <div class="border rounded p-1">
                <div class="border rounded text-center p-4">
                  <i class="fa fa-hotel fa-2x text-primary mb-2"></i>
                  <h2 class="mb-1" data-toggle="counter-up"><%=daoRoom.getNumberRoom()%></h2>
                  <p class="mb-0">Rooms</p>
                </div>
              </div>
            </div>
            <div class="col-sm-4 wow fadeIn" data-wow-delay="0.3s">
              <div class="border rounded p-1">
                <div class="border rounded text-center p-4">
                  <i class="fa fa-users-cog fa-2x text-primary mb-2"></i>
                  <h2 class="mb-1" data-toggle="counter-up"><%=employeeDao.getNumberEmployees()%></h2>
                  <p class="mb-0">Staffs</p>
                </div>
              </div>
            </div>
            <div class="col-sm-4 wow fadeIn" data-wow-delay="0.5s">
              <div class="border rounded p-1">
                <div class="border rounded text-center p-4">
                  <i class="fa fa-users fa-2x text-primary mb-2"></i>
                  <h2 class="mb-1" data-toggle="counter-up"><%=clientDao.getNumberClients()%></h2>
                  <p class="mb-0">Clients</p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="row g-3">
            <div class="col-6 text-end">
              <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.1s" src="img/about-1.jpg" style="margin-top: 25%;">
            </div>
            <div class="col-6 text-start">
              <img class="img-fluid rounded w-100 wow zoomIn" data-wow-delay="0.3s" src="img/about-2.jpg">
            </div>
            <div class="col-6 text-end">
              <img class="img-fluid rounded w-50 wow zoomIn" data-wow-delay="0.5s" src="img/about-3.jpg">
            </div>
            <div class="col-6 text-start">
              <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.7s" src="img/about-4.jpg">
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- About End -->


  <!-- Room Start -->

  <div class="container-xxl py-5">
    <div class="container">
      <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
        <h6 class="section-title text-center text-primary text-uppercase">Our Rooms</h6>
        <h1 class="mb-5">Explore Our <span class="text-primary text-uppercase">Rooms</span></h1>
      </div>
      <div class="row g-2">
      <%
        RoomCategoryDao dao = new RoomCategoryDao();
        List<RoomCategory> roomCategories = dao.getAllRoomCategories();
        for (RoomCategory category : roomCategories) {
      %>
        <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
          <div class="room-item shadow rounded overflow-hidden">
            <div class="position-relative">
              <img class="img-fluid" src="data:image/jpg;base64, <%= category.getRoomImage()%>" alt="">
              <small class="position-absolute start-0 top-100 translate-middle-y bg-primary text-white rounded py-1 px-3 ms-4"><%= category.getPrice() %> Dh/Night</small>
            </div>
            <div class="p-4 mt-2">
              <div class="d-flex justify-content-between mb-3">
                <h5 class="mb-0"><%= category.getName() %> Room</h5>
                <div class="ps-2">
                  <small class="fa fa-star text-primary"></small>
                  <small class="fa fa-star text-primary"></small>
                  <small class="fa fa-star text-primary"></small>
                  <small class="fa fa-star text-primary"></small>
                  <small class="fa fa-star text-primary"></small>
                </div>
              </div>
              <div class="d-flex mb-3">
              </div>
              <p class="text-body mb-3">Erat ipsum justo amet duo et elitr dolor, est duo duo eos lorem sed diam stet diam sed stet lorem.</p>
            </div>
          </div>
        </div>
      <%
        }
      %>
      </div>
    </div>
  </div>
</div>
<!-- Room End -->

  <!-- Booking Start -->
  <div class="container-xxl py-5">
    <div class="container">
      <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
        <h6 class="section-title text-center text-primary text-uppercase">Room Booking</h6>
        <h1 class="mb-5">Book A <span class="text-primary text-uppercase">Luxury Room</span></h1>
      </div>
      <div class="row g-5">
        <div class="col-lg-6">
          <div class="row g-3">
            <div class="col-6 text-end">
              <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.1s" src="img/about-1.jpg" style="margin-top: 25%;">
            </div>
            <div class="col-6 text-start">
              <img class="img-fluid rounded w-100 wow zoomIn" data-wow-delay="0.3s" src="img/about-2.jpg">
            </div>
            <div class="col-6 text-end">
              <img class="img-fluid rounded w-50 wow zoomIn" data-wow-delay="0.5s" src="img/about-3.jpg">
            </div>
            <div class="col-6 text-start">
              <img class="img-fluid rounded w-75 wow zoomIn" data-wow-delay="0.7s" src="img/about-4.jpg">
            </div>
          </div>
        </div>
        <div class="col-lg-6">
          <form action="${pageContext.request.contextPath}/reservation" method="post">
            <div class="row g-3">
              <div class="col-md-6">
                <div class="form-floating">
                  <input type="email" class="form-control" id="email1" name="email" placeholder="Your Email" required>
                  <label for="email1">Your Email</label>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-floating">
                  <input type="text" class="form-control" id="identification" name="identification" placeholder="Your Identification" required>
                  <label for="identification">Your Identification</label>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-floating">
                  <input type="tel" class="form-control" id="phoneNum" name="phoneNum" placeholder="Your Phone Number" required>
                  <label for="phoneNum">Your Phone Number</label>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-floating">
                  <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Your Full Name" required>
                  <label for="fullName">Your Full Name</label>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-floating">
                  <input type="text" class="form-control" id="checkInDate" name="checkInDate" placeholder="Check-in Date (YYYY-MM-DD)" required>
                  <label for="checkInDate">Check-in Date (YYYY-MM-DD)</label>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-floating">
                  <input type="text" class="form-control" id="checkOutDate" name="checkOutDate" placeholder="Check-out Date (YYYY-MM-DD)" required>
                  <label for="checkOutDate">Check-out Date (YYYY-MM-DD)</label>
                </div>
              </div>

              <div class="col-md-12">
                <div class="form-floating">
                  <select class="form-select" id="roomCategory" name="roomCategory" required>
                    <option selected disabled>Select Room Category</option>
                    <option value="1">King</option>
                    <option value="2">Single</option>
                    <option value="3">Double</option>
                    <option value="4">Suite</option>
                  </select>
                  <label for="roomCategory">Room Category</label>
                </div>
              </div>
              <div class="col-12">
                <button class="btn btn-primary w-100 py-3" type="submit">Book Now</button>
              </div>
<%--              <%--%>
<%--                ReservationServlet reservationServlet = new ReservationServlet();--%>
<%--                String result = (String) reservationServlet.getServletContext().getAttribute("return_msg");--%>
<%--                if(result.equals("true")){--%>
<%--              %>--%>
<%--              <p>your booking is reserved successfully </p>--%>
<%--              <h3> you can download your reservation ticket (it's important the day of your coming</h3>--%>
<%--              <button class="btn btn-primary w-100 py-3" type="submit" name="action" value="secondServlet">Download ticket</button>--%>
<%--              <%} else{--%>
<%--              %>--%>
<%--              <p>there is an error ; try to check your information or the Room is not available for the selected dates.</p>--%>
<%--              <%--%>
<%--                }--%>
<%--              %>--%>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>

</div>

<!-- Booking End -->

<!-- Service Start -->
<div class="container-xxl py-5">
  <div class="container">
    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
      <h6 class="section-title text-center text-primary text-uppercase">Our Services</h6>
      <h1 class="mb-5">Explore Our <span class="text-primary text-uppercase">Services</span></h1>
    </div>
    <div class="row g-4">
      <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
        <a class="service-item rounded" href="">
          <div class="service-icon bg-transparent border rounded p-1">
            <div class="w-100 h-100 border rounded d-flex align-items-center justify-content-center">
              <i class="fa fa-hotel fa-2x text-primary"></i>
            </div>
          </div>
          <h5 class="mb-3">Rooms & Appartment</h5>
          <p class="text-body mb-0">Erat ipsum justo amet duo et elitr dolor, est duo duo eos lorem sed diam stet diam sed stet lorem.</p>
        </a>
      </div>
      <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.2s">
        <a class="service-item rounded" href="">
          <div class="service-icon bg-transparent border rounded p-1">
            <div class="w-100 h-100 border rounded d-flex align-items-center justify-content-center">
              <i class="fa fa-utensils fa-2x text-primary"></i>
            </div>
          </div>
          <h5 class="mb-3">Food & Restaurant</h5>
          <p class="text-body mb-0">Erat ipsum justo amet duo et elitr dolor, est duo duo eos lorem sed diam stet diam sed stet lorem.</p>
        </a>
      </div>
      <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
        <a class="service-item rounded" href="">
          <div class="service-icon bg-transparent border rounded p-1">
            <div class="w-100 h-100 border rounded d-flex align-items-center justify-content-center">
              <i class="fa fa-spa fa-2x text-primary"></i>
            </div>
          </div>
          <h5 class="mb-3">Spa & Fitness</h5>
          <p class="text-body mb-0">Erat ipsum justo amet duo et elitr dolor, est duo duo eos lorem sed diam stet diam sed stet lorem.</p>
        </a>
      </div>
      <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.4s">
        <a class="service-item rounded" href="">
          <div class="service-icon bg-transparent border rounded p-1">
            <div class="w-100 h-100 border rounded d-flex align-items-center justify-content-center">
              <i class="fa fa-swimmer fa-2x text-primary"></i>
            </div>
          </div>
          <h5 class="mb-3">Sports & Gaming</h5>
          <p class="text-body mb-0">Erat ipsum justo amet duo et elitr dolor, est duo duo eos lorem sed diam stet diam sed stet lorem.</p>
        </a>
      </div>
      <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
        <a class="service-item rounded" href="">
          <div class="service-icon bg-transparent border rounded p-1">
            <div class="w-100 h-100 border rounded d-flex align-items-center justify-content-center">
              <i class="fa fa-glass-cheers fa-2x text-primary"></i>
            </div>
          </div>
          <h5 class="mb-3">Event & Party</h5>
          <p class="text-body mb-0">Erat ipsum justo amet duo et elitr dolor, est duo duo eos lorem sed diam stet diam sed stet lorem.</p>
        </a>
      </div>
      <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.6s">
        <a class="service-item rounded" href="">
          <div class="service-icon bg-transparent border rounded p-1">
            <div class="w-100 h-100 border rounded d-flex align-items-center justify-content-center">
              <i class="fa fa-dumbbell fa-2x text-primary"></i>
            </div>
          </div>
          <h5 class="mb-3">GYM & Yoga</h5>
          <p class="text-body mb-0">Erat ipsum justo amet duo et elitr dolor, est duo duo eos lorem sed diam stet diam sed stet lorem.</p>
        </a>
      </div>
    </div>
  </div>
</div>
<!-- Service End -->

<!-- Contact Start -->
<div class="container-xxl py-5">
  <div class="container">
    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
      <h6 class="section-title text-center text-primary text-uppercase">Contact Us</h6>
      <h1 class="mb-5"><span class="text-primary text-uppercase">Contact</span> For Any Query</h1>
    </div>
    <div class="row g-4">
      <div class="col-12">
        <div class="row gy-4">
          <div class="col-md-4">
            <h6 class="section-title text-start text-primary text-uppercase">Booking</h6>
            <p><i class="fa fa-envelope-open text-primary me-2"></i>hotelier5stars@gmail.comm</p>
          </div>
          <div class="col-md-4">
            <h6 class="section-title text-start text-primary text-uppercase">General</h6>
            <p><i class="fa fa-envelope-open text-primary me-2"></i>jabbourproject@gmail.com</p>
          </div>
          <div class="col-md-4">
            <h6 class="section-title text-start text-primary text-uppercase">Technical</h6>
            <p><i class="fa fa-envelope-open text-primary me-2"></i>hotelierTech@gmail.com</p>
          </div>
        </div>
      </div>
      <div class="col-md-6 wow fadeIn" data-wow-delay="0.1s">
        <iframe class="position-relative rounded w-100 h-100"
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3001156.4288297426!2d-78.01371936852176!3d42.72876761954724!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4ccc4bf0f123a5a9%3A0xddcfc6c1de189567!2sNew%20York%2C%20USA!5e0!3m2!1sen!2sbd!4v1603794290143!5m2!1sen!2sbd"
                frameborder="0" style="min-height: 350px; border:0;" allowfullscreen="" aria-hidden="false"
                tabindex="0"></iframe>
      </div>
      <div class="col-md-6">
        <div class="wow fadeInUp" data-wow-delay="0.2s">
          <form action="${pageContext.request.contextPath}/ContactServlet" method="post">
            <div class="row g-3">
              <div class="col-md-6">
                <div class="form-floating">
                  <input type="text" class="form-control" id="name" name="name" placeholder="Your Name">
                  <label for="name">Your Name</label>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-floating">
                  <input type="email" class="form-control" id="email" name="email" placeholder="Your Email">
                  <label for="email">Your Email</label>
                </div>
              </div>
              <div class="col-12">
                <div class="form-floating">
                  <input type="text" class="form-control" id="subject"  placeholder="Subject">
                  <label for="subject">Subject</label>
                </div>
              </div>
              <div class="col-12">
                <div class="form-floating">
                  <textarea class="form-control" placeholder="Leave a message here" name="message" id="message" style="height: 150px"></textarea>
                  <label for="message">Message</label>
                </div>
              </div>
              <div class="col-12">
                <button class="btn btn-primary w-100 py-3" type="submit">Send Message</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Contact End -->


<!-- Footer Start -->
<div class="container-fluid bg-dark text-light footer wow fadeIn"  style="margin-top: 50px" data-wow-delay="0.1s">
  <div class="container pb-5">
    <div class="row g-5">
      <div class="col-md-6 col-lg-4">
        <div class="bg-primary rounded p-4">
          <a href="index.jsp"><h1 class="text-white text-uppercase mb-3">Hotelier</h1></a>
          <p class="text-white mb-0">
            Download <a class="text-dark fw-medium" href="https://htmlcodex.com/hotel-html-template-pro">Hotelier – Premium Version</a>, build a professional website for your hotel business and grab the attention of new visitors upon your site’s launch.
          </p>
        </div>
      </div>
      <div class="col-md-6 col-lg-3">
        <h6 class="section-title text-start text-primary text-uppercase mb-4">Contact</h6>
        <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, New York, USA</p>
        <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
        <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@example.com</p>
        <div class="d-flex pt-2">
          <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
          <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
          <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
          <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
        </div>
      </div>
      <div class="col-lg-5 col-md-12">
        <div class="row gy-5 g-4">
          <div class="col-md-6">
            <h6 class="section-title text-start text-primary text-uppercase mb-4">Company</h6>
            <a class="btn btn-link" href="">About Us</a>
            <a class="btn btn-link" href="">Contact Us</a>
            <a class="btn btn-link" href="">Privacy Policy</a>
            <a class="btn btn-link" href="">Terms & Condition</a>
            <a class="btn btn-link" href="">Support</a>
          </div>
          <div class="col-md-6">
            <h6 class="section-title text-start text-primary text-uppercase mb-4">Services</h6>
            <a class="btn btn-link" href="">Food & Restaurant</a>
            <a class="btn btn-link" href="">Spa & Fitness</a>
            <a class="btn btn-link" href="">Sports & Gaming</a>
            <a class="btn btn-link" href="">Event & Party</a>
            <a class="btn btn-link" href="">GYM & Yoga</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="copyright">
      <div class="row">
        <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
          &copy; <a class="border-bottom" href="#">Your Site Name</a>, All Right Reserved.

          <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
          Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a>
        </div>
        <div class="col-md-6 text-center text-md-end">
          <div class="footer-menu">
            <a href="">Home</a>
            <a href="">Cookies</a>
            <a href="">Help</a>
            <a href="">FQAs</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Footer End -->


<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
<script>
  function checkAvailability() {
    console.log('Function called');
    $.ajax({
      type: 'GET',
      url: 'CheckAvailabilityServlet',
      dataType: 'json',
      success: function(response) {
        if (response && response.message) {
          // Update the content of the message container
          $('#messageContainer').html('<p>' + response.message + '</p>');
        } else {
          console.error('Unexpected response format:', response);
        }
      },
      error: function() {
        // Handle errors if necessary
      }
    });
  }
</script>
</body>
</html>