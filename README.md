LocaSend
===========
---
*Share your location instantly*

This is a simple android app that shows the current location (longitude, latitude). Then, with a button click, you can share your current location using other apps. 

---

**SharingIntent**
1. You first tell the system what kind of intent you will use.   
`Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);`
2. Next you set the type.    
`sharingIntent.setType("text/plain");`
3. Then you set the Extra flags. Something like this :    
`sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, YOUR_TEXT_HERE);`
4. Lastly, you should start the share action chooser.    
`startActivity(Intent.createChooser(sharingIntent, "YOUR_CUSTOM_TITLE"));`

---
**Location Manager and Criteria**
1. First you must declare permission to access `location` and `internet`.   
`<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />`       
`<uses-permission android:name="android.permission.INTERNET" />`
2. Next, you should get `LOCATION_SERVICE` from `LocationManger` and make new `Criteria` variable.   
`locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); `   
`Criteria criteria = new Criteria();`
3. Request location updates. Parameters are `String provider, long minTime, float minDistance, LocationListener listener`.
Example code is :   
`Location location = locationManager.getLastKnownLocation(mprovider);`       
`locationManager.requestLocationUpdates(mprovider, 15000, 1, this);`

---
**Some code in the project that you may find it useful**  
When you convert a string to a double, you should make the following changes.   
`String LONGITUDE_HERE="";`
`String LATITUDE_HERE="";`      
and   
`LONGITUDE_HERE= String.valueOf(location.getLongitude());`
`LATITUDE_HERE= String.valueOf(location.getLatitude());`  
You can find more information [here](http://stackoverflow.com/questions/43674075/set-text-retreived-by-location-manager-inside-sharingintent-extra/).

---
**Permissions Dealing from 6.0**  
*From 6.0, you should get permission check by users. You must add this line in the proper place if your app targets 6.0, too.*  
*`if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) { return; }`*

---
**License** (You can find the same one under the file name LICENSE)
        
        Copyright 2017 cozyplanes

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.

