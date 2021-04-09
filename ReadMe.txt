README

Once the program starts, the user will be prompted for the input, namely the Furniture Category, Furniture Type and
the number of items which are a part of that order. Accepted responses are only case insensitive when it comes to
the first letter of input. For example, "Chair" and "chair" are both accepted, but "CHAIR" and "cHAIr" is not.
The same is applied to type of furniture.


Once the program ends, if the order has been fulfilled, the correct information will be printed
to orderform.txt which is generated in the working directory.
If the order was not fulfilled, the manufacturer data will also be printed to orderform.txt
The orderform.txt is overwritten each time.


For our JUNIT tests, it requires the use of the original database which was uploaded to D2L. Please ensure that no
values from the database have been added or deleted before running tests. Where specfied, the tests will add their 
own values into the database before running tests specifically just for testing purposes. 