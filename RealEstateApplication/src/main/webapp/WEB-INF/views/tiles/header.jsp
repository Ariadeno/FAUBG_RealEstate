<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="header">
     <header>
                <section id="banner">
               
                </section>

                 <nav> 
                        <ul>
                        <li> <a href="/rea/">Home</a></li>
                        <li><a href=""> Buy </a></li>
                        <li><a href=""> Rent </a></li>
                        <li id="loginknop">
                        <a href=${AccountUrl }>${LoginTitle}</a>
                        <c:if test = "${LoginTitle == 'My Account'}">
                        	&nbsp;&nbsp;<a href="/rea/logout">(Logout)</a>
                        </c:if>
                        </li>           
                        </ul>
           		 </nav>
             
            </header>
</div>