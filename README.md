# Introduction

Coding take-home task for Patchwork, provides basic simulation of a book lending library in kotlin.

## Requirements

Context:

I have many books which I would like to share with my community. That sounds like a book-lending library. Please write some software to help me do that.

Stories:

- As a library user, I would like to be able to find books by my favourite author, so that I know if they are available in the library.
- As a library user, I would like to be able to find books by title, so that I know if they are available in the library.
- As a library user, I would like to be able to find books by ISBN, so that I know if they are available in the library.
- As a library user, I would like to be able to borrow a book, so I can read it at home.
- As the library owner, I would like to know how many books are being borrowed, so I can see how many are outstanding.
- As a library user, I should be to prevented from borrowing reference books, so that they are always available.

## Implementation

As per the requirements no user interface is provided. 
The logic is exposed through acceptance and unit tests which together provide 100% line coverage.
It is also assumed that user authentication and authorisation would be implemented outside this logic

## To build 
./gradlew build

# To Run and View Tests
./gradlew cleanTest test && open build/reports/tests/test/index.html

## Missing Features
User Management: adding and deleting users
Book Management: adding books to the data store
Loan limits: time/number of books per borrower.
Pagination of search and report results.
Loans: Returning books
Persistent storage: e.g. PostgreSQL or Sqlite.
Any form of user interface. 
Any form of API
User authentication authorisation. 
CI/CD deployment etc.

