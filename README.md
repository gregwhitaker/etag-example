# etag-example
![Build](https://github.com/gregwhitaker/etag-example/workflows/Build/badge.svg)

An example of shallow ETags in Spring Boot.

Shallow ETags only provide benefits to the client as they still require the same processing as any other request on the server side.

## Prerequisites
This example requires a running PostgreSQL database.

You can start an instance as a Docker container by running the following command:

    docker run -d -p 5432:5432 postgres

## Building the Example
Run the following command to build the example application as a Docker image:

    ./gradlew clean buildImage
    
## Running the Example
Follow the steps below to run the example:

1. Ensure you have a running PostgreSQL database on port `5432`. If you do not, you can start one in a Docker container by
running the following command:

        docker run -d -p 5432:5432 postgres

2. Start the example application by running the following command:

        docker run --rm -e SPRING_PROFILES_ACTIVE='localdocker' -p 8080:8080 gregnetifi/etag-example
        
3. In a new terminal, run the following to get the product information for product `001`:

        curl -v http://localhost:8080/products/001
        
    If successful, you will receive a large JSON response with the product information.
    
4. Get the value of the `ETag` header from the response:

        ETag: "09564cf9d39419c13e93fe29bb4867a8b"
        
5. Run the following command to retrieve the product information for product `001` again, but this time specify the ETag to check in the `If-None-Match` header:

        curl -v --header "If-None-Match: \"09564cf9d39419c13e93fe29bb4867a8b\"" http://localhost:8080/products/001
        
    If successful, you will see that a `304 - Not Modified` HTTP status code was returned. This indicates that the object has not changed since you last requested it.
    Since the object has not changed it was not returned to prevent unnecessary client-side processing.
    
6. Now, run the following command to modify the `active` state of the product to `false`:

        curl -v -X DELETE http://localhost:8080/products/001/active
        
    If successful, you will receive a `204 - No Content` HTTP status code.
    
7. Next, run the GET command with the ETag you previously ran (below for convenience) to retrieve the product. 

        curl -v --header "If-None-Match: \"09564cf9d39419c13e93fe29bb4867a8b\"" http://localhost:8080/products/001
        
    Notice that the entire JSON response was returned with a new `ETag` header. This is because the product has been modified since you last requested it.
    
## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/etag-example/issues).

## License
MIT License

Copyright (c) 2019 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
