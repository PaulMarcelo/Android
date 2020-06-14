# CheckBlurImage
CheckBlurImage
Simple application that through a web service evaluates whether an image is blurred or not.
![alt text](https://github.com/PaulMarcelo/Android/blob/master/CheckBlurImage/Captura123456789.PNG)

The application connects to a web service made in java (Spring Boot) that is in charge of evaluating the image,
it is an alternative to using OpenVC directly in the Android project, which implies an increase in the size of the generator apk.

If you want to directly add OpenVC to the Android project, go to this link:
[ImageBlur Detection](https://github.com/jainullas/ImageBlurDetection)

The java project that contains the web service is the following:
[DetectImageBlur](https://github.com/PaulMarcelo/SpringBoot/blob/master/DetectImageBlur/README.md)
