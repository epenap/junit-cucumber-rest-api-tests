Feature: Get book by ISBN using endpoint https://www.googleapis.com/books/v1/volumes
	Scenario: User calls Google web service to get a book by its ISBN
		Given a book exists with an isbn of 0345341465
		When a user retrieves the book by isbn
		Then the response status code is 200
		And response includes the following
			| totalItems | 1 |
			| kind | books#volumes |
		And response includes the following in any order
			| items.volumeInfo.title | Star Wars |
			| items.volumeInfo.publisher | Lucasbooks |
			| items.volumeInfo.pageCount | 224 |
	Scenario: User calls Google web service to get a book using non-matching Query
		Given a book does not exist with an isbn of nonmatchingqueryforbooks
		When a user retrieves the book by isbn
		Then the response status code is 200
		And response includes the following
			| totalItems | 0 |
			| kind | books#volumes |
	Scenario: User calls Google web service to get a book using no queries
		Given a user does not specify an isbn to search by
		When a user retrieves the book by isbn
		Then the response status code is 400