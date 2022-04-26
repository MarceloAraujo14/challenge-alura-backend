Deciding the approach to the problem:[solved]


First I had try to approach the problem from the microsservice perspective, building the services
separately. But find it was going to be more dificult and got back to build a modular monolitic
service.
<br><br>

---
Building the FrontEnd Form to submit Files:[solved]

First issue was to build the front-end form to upload the file.
I've manage to use the thymeleaf api to integrate with java and springboot. 
I've build the controller using SpringBoot MultipartFile to receive the file submit from the
form as RequestParam.

---
Returning sucess or error message on the front:[solved]

Throw exception with Exception message on the service layer and catch the message on the controller,
adding the message on model and redirect to the html.


--- 
Error "No ParameterResolver registered for parameter" on class TransactionValidationTest:[solved]

Insert @ExtendWith(MockitExtension.class) and @ContextConfiguration(classes = {TransactionTest.class})
on the class and @InjectMocks on the TransactionValidation attribute

---
UnmodifiableList error on TransactionValidationTest: [solved]
The received list parameter on TransactionValidation is a Interface, wich cannot be modified.
Just build another list using new ArrayList and insert the received list on it. Now, you can modify
the list and return it.
---
