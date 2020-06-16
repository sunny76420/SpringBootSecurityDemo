# SpringBootSecurityDemo
#Cross-Site Request Forgery.

In this branch CSRF is enabled.

When using Postman or any other tool to POST, PUT and DELETE XSRF-Token is required.

Steps to generate XSRF Token:

Step 1: Send a GET request to the API after enabling Postman interceptor.

Step 2: Check the headers section of response and you will find the XSRF-Token.

Steps to use XSRF Token:

Step 1: Create a POST, PUT or DELETE request.

Step 2: Add a header with key: "XSRF-Token" and value as the token received from GET request.
