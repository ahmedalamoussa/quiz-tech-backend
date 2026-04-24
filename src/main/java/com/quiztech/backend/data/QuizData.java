package com.quiztech.backend.data;

import com.quiztech.backend.entity.Quiz;
import com.quiztech.backend.entity.User;
import java.util.Arrays;
import java.util.List;

public class QuizData {

    public static List<Quiz> getQuizzes(User creator) {
        return Arrays.asList(
                // JAVA (5 quiz)
                new Quiz().builder()
                        .title("Java Basics")
                        .description("Fundamentals of Java programming")
                        .category("Java")
                        .difficulty(1)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Java OOP Concepts")
                        .description("Object-Oriented Programming in Java")
                        .category("Java")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Java Collections Framework")
                        .description("Lists, Sets, Maps and more")
                        .category("Java")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Java Exceptions & Error Handling")
                        .description("Try-catch, throws and exception management")
                        .category("Java")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Java Advanced - Streams & Lambda")
                        .description("Functional programming in Java")
                        .category("Java")
                        .difficulty(4)
                        .createdBy(creator)
                        .build(),

                // C (5 quiz)
                new Quiz().builder()
                        .title("C Programming Basics")
                        .description("Introduction to C language")
                        .category("C")
                        .difficulty(1)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("C Pointers & Memory Management")
                        .description("Understanding pointers and malloc/free")
                        .category("C")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("C Arrays & Strings")
                        .description("Working with arrays and string manipulation")
                        .category("C")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("C Structures & Unions")
                        .description("Data structures in C")
                        .category("C")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("C File I/O & Advanced")
                        .description("File operations and advanced C concepts")
                        .category("C")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),

                // C++ (5 quiz)
                new Quiz().builder()
                        .title("C++ Basics")
                        .description("Introduction to C++")
                        .category("C++")
                        .difficulty(1)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("C++ OOP - Classes & Objects")
                        .description("Object-Oriented Programming in C++")
                        .category("C++")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("C++ Inheritance & Polymorphism")
                        .description("Advanced OOP concepts")
                        .category("C++")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("C++ STL - Containers & Iterators")
                        .description("Standard Template Library")
                        .category("C++")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("C++ Templates & Advanced Features")
                        .description("Generic programming and modern C++")
                        .category("C++")
                        .difficulty(4)
                        .createdBy(creator)
                        .build(),

                // Arduino (3 quiz)
                new Quiz().builder()
                        .title("Arduino Basics")
                        .description("Getting started with Arduino")
                        .category("Arduino")
                        .difficulty(1)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Arduino GPIO & Sensors")
                        .description("Digital and analog I/O with sensors")
                        .category("Arduino")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Arduino IoT & Communication")
                        .description("WiFi, Bluetooth and serial communication")
                        .category("Arduino")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),

                // Python (5 quiz)
                new Quiz().builder()
                        .title("Python Basics")
                        .description("Introduction to Python programming")
                        .category("Python")
                        .difficulty(1)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Python Data Structures")
                        .description("Lists, tuples, dictionaries and sets")
                        .category("Python")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Python OOP")
                        .description("Classes, inheritance and polymorphism")
                        .category("Python")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Python Libraries - NumPy & Pandas")
                        .description("Data science with Python")
                        .category("Python")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Python Flask & Django")
                        .description("Web development with Python")
                        .category("Python")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),

                // JavaScript (4 quiz)
                new Quiz().builder()
                        .title("JavaScript Basics")
                        .description("Introduction to JavaScript")
                        .category("JavaScript")
                        .difficulty(1)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("JavaScript DOM Manipulation")
                        .description("Working with the DOM")
                        .category("JavaScript")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("JavaScript Async & Promises")
                        .description("Callbacks, promises and async/await")
                        .category("JavaScript")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("JavaScript ES6+ Features")
                        .description("Modern JavaScript features")
                        .category("JavaScript")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),

                // React.js (4 quiz)
                new Quiz().builder()
                        .title("React.js Basics")
                        .description("Introduction to React")
                        .category("React.js")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("React Components & Props")
                        .description("Functional and class components")
                        .category("React.js")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("React Hooks & State Management")
                        .description("useState, useEffect and custom hooks")
                        .category("React.js")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("React Router & Context API")
                        .description("Navigation and state management")
                        .category("React.js")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),

                // Angular (4 quiz)
                new Quiz().builder()
                        .title("Angular Basics")
                        .description("Introduction to Angular framework")
                        .category("Angular")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Angular Components & Templates")
                        .description("Building Angular components")
                        .category("Angular")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Angular Services & Dependency Injection")
                        .description("Angular DI and services")
                        .category("Angular")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Angular RxJS & Observables")
                        .description("Reactive programming in Angular")
                        .category("Angular")
                        .difficulty(4)
                        .createdBy(creator)
                        .build(),

                // Vue.js (3 quiz)
                new Quiz().builder()
                        .title("Vue.js Basics")
                        .description("Introduction to Vue.js")
                        .category("Vue.js")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Vue.js Components & Props")
                        .description("Building with Vue components")
                        .category("Vue.js")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("Vue.js State Management - Vuex")
                        .description("Managing state with Vuex")
                        .category("Vue.js")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),

                // TypeScript (2 quiz)
                new Quiz().builder()
                        .title("TypeScript Basics")
                        .description("Introduction to TypeScript")
                        .category("TypeScript")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("TypeScript Advanced Types")
                        .description("Generics, interfaces and advanced types")
                        .category("TypeScript")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),

                // SQL (2 quiz)
                new Quiz().builder()
                        .title("SQL Basics")
                        .description("Introduction to SQL")
                        .category("SQL")
                        .difficulty(1)
                        .createdBy(creator)
                        .build(),
                new Quiz().builder()
                        .title("SQL Advanced - Joins & Optimization")
                        .description("Complex queries and optimization")
                        .category("SQL")
                        .difficulty(3)
                        .createdBy(creator)
                        .build(),

                // MongoDB (1 quiz)
                new Quiz().builder()
                        .title("MongoDB & NoSQL")
                        .description("Document-oriented databases")
                        .category("MongoDB")
                        .difficulty(2)
                        .createdBy(creator)
                        .build(),

                // Git (1 quiz)
                new Quiz().builder()
                        .title("Git & Version Control")
                        .description("Git commands and workflows")
                        .category("Git")
                        .difficulty(1)
                        .createdBy(creator)
                        .build(),

                // Docker (1 quiz)
                new Quiz().builder()
                        .title("Docker Basics")
                        .description("Containerization with Docker")
                        .category("Docker")
                        .difficulty(2)
                        .createdBy(creator)
                        .build()
        );
    }
}