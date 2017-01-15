# InstagramTags
Sample MVVM project uses instagram API. Supported two-pane layouts for tablets.

#Screenshots
<img src="https://raw.githubusercontent.com/iammert/InstagramTags/master/screeshots/mobile_mockup.png"/>
<img src="https://raw.githubusercontent.com/iammert/InstagramTags/master/screeshots/tablet_mockup.png"/>

#Technology used
* Clean MVVM architecture
* [Android Databinding](https://developer.android.com/topic/libraries/data-binding/index.html)
* [Dagger 2](https://google.github.io/dagger/)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [OkHttp](http://square.github.io/okhttp/)
* [Retfofit](https://square.github.io/retrofit/)
* [Picasso](http://square.github.io/picasso/)

#Architectural Design
<img src="https://raw.githubusercontent.com/iammert/InstagramTags/master/screeshots/arch.png"/>

#Setup
Add your client_id and redirect_uri to Constants class for authentication. For more detail you can follow [instagram developer documentation](https://www.instagram.com/developer/).
```java
public static final String CLIENT_ID = "REPLACE_HERE";
public static final String REDIRECT_URI = "REPLACE_HERE";
public static final String RESPONSE_TYPE = "token"; //you can change auth type
public static final String SCOPE = "public_content"; //you can change auth scope
```




License
--------


    Copyright 2017 Mert Şimşek.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

