# Comp2000CW2
---------------------------
**Third Party Dependancies**
---------------------------
CircleImageView - https://github.com/hdodenhof/CircleImageView
*For getting circle images as thumbnails in View projects page list item*
>implementation 'de.hdodenhof:circleimageview:3.1.0'

Volley - https://developer.android.com/training/volley
*Responsible for CRUD Operations*
>implementation 'com.android.volley:volley:1.2.1'

RetroFit - https://square.github.io/retrofit/
*Responsible for Post on image*
>implementation 'com.squareup.retrofit2:retrofit:2.9.0' '           '
>implementation 'com.squareup.retrofit2:converter-gson:2.3.0'

Glide - https://github.com/bumptech/glide
*Responsible for Displaying images from URL*
>implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'


LifeCycle - https://developer.android.com/jetpack/androidx/releases/lifecycle
>def lifecycleVersion = "1.1.1"
    implementation "android.arch.lifecycle:extensions:$lifecycleVersion"
    
-----------------------------
**How To Use The Application**

To run and login all the user has to do is input a user id as an integer the first and last names in the name box as well as a password this would allow the user to sign in
