# JCenterUploader

A gradle script for uploading to Bintray Server.

# Set the project information

You have a `gradle.properties` file in your project original. Adding the setting in the end of your `gradle.properties` file.

For an example ass below.

```
## Project information
proj_repo=repo
proj_name=projectname
proj_libname=ProjectName
proj_group=com.taiwan.no.one
proj_artifactid=projectname
proj_version=0.0.1
proj_description=YOUR PROJECT DESCRIPTION
proj_websiteurl=YOUR PROJECT WEBSITE URL
proj_issuetrackerurl=YOUR PROJECT ISSUE TRACKER URL
proj_vcsurl=YOUR PROJECT VERSION CONTROL URL

## License information
license_name='The Apache Software License, Version 2.0'
license_url='http://www.apache.org/licenses/LICENSE-2.0.txt'

## Developer information
developer_id=XXX
developer_name=XXX
developer_email=XXX@XXX.XXX
```

# Set the user information

You have an other `local.properties` file in your project, it should be hided normally by `.gitignore`. The ignore file can protect your private information, git will ignore that file, doesn't let it be pushed.

```
bintray.user=BINTRAY USER
bintray.apikey=*******************
```

# Setup bintray uploader script

There are two ways for putting your library to Bintray.

### First: using remote upload gradle file

Here is my created gradle file's url [uploader.gradle](https://github.com/pokk/JCenterUploader/blob/master/Uploader.gradle). You should add this path to your end of `builde.gradle` file.

Final file will be as below:

```gradle
android {
    compileSdkVersion 25
    buildToolsVersion "25.0.2"

    defaultConfig {
        minSdkVersion 19
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"

    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })

    compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"

    testCompile 'junit:junit:4.12'
}

apply from: 'https://raw.githubusercontent.com/pokk/JCenterUploader/master/Uploader.gradle'
```

### Second: using local gradle file

The way is almost the same as `First way`.
You can download my file or create a gradle script by yourself. Put it to your `library module` folder, then adding `apply from: 'Uploader.gradle'`, as below:

```gradle
android {
    compileSdkVersion 25
    buildToolsVersion "25.0.2"

    defaultConfig {
        minSdkVersion 19
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"

    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })

    compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"

    testCompile 'junit:junit:4.12'
}

apply from: 'Uploader.gradle'
```

# Run your script and push them to Bintray

Under the Mac OS environment.

1. Open terminal window
2. exec ./gradlew install
3. exec ./gradlew bintrayUpload
