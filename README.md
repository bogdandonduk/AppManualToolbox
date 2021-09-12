
# AppManualToolbox  
  
  Android library based on the enhanced builder pattern that simplifies creation of a typical app guide or app manual activities comprised of ViewPager2 with dots indicator. 
  They can be built and opened like regular activities and as welcome screens. In the last use case, they manage first launch setting on their own.
  
## Include in your project  
**Gradle dependency**  
  
Add this in your **app**-level **build.gradle** file:  
```groovy  
dependencies {  
	...  
  
	def latest_version_tag = 2.2 
	implementation "com.github.bogdandonduk:AppManualToolbox:$latest_version_tag"  
  
	...  
}  
```  
You can always find the **latest_version_tag** [here](https://github.com/bogdandonduk/AppManualToolbox/releases).  
  
Also make sure you have this repository in your **project**-level **build.gradle** file:  
```groovy  
allprojects {  
	repositories {  
		...  
  
		maven { url 'https://jitpack.io' }  
	}  
}  
```  

# Examples of usage
```kotlin 
// if you want to open the built app manual activity as a regular one
// then firstly obtain a builder via static method of AppManualToolbox object like this:
val builder = AppManualToolbox.buildAppManual()

// build your activity
vabuilder
	.setBackgroundColor { oldColor: Int -> // this is a feature of the enhanced builder pattern, 
	// it provides previous values as lambda arguments for you to operate on.
	// just return a new value from this lambda to set it
	// p.s. Do not worry about performance, this lambda is inlined.
		Color.WHITE
	}
	.setTitle { oldTitle: Title ->
		Title(text = "Welcome!", textColor = Color.BLACK)
	}
	.setAllPages { oldPages: MutableList<PageItem> ->
		mutableListOf<PageItem>.apply {
			add(
				PageItem(
					text = "Our app is very useful. We hope you will delight using it",
					bigImageDrawable = getDrawable(R.drawable.big_app_logo)
				)
			)
			add(
				PageItem(
					text = "It can do this such an amazing thing, and many others. Be productive!",
					bigImageDrawable = context.getDrawable(R.drawable.big_app_logo)
				)
			)
		}
	}
	.setStartButton { oldButton: StartButton ->
		StartButton(
			text = "Start!",
			textColor = Color.WHITE,
			backgroundColor = Color.CYAN
		)
	}
	.setIsSlidable { oldValue: Boolean ->
		true // this will also add swipe-to-dismiss functionality
	}

// then pass the model built by the builder to this static method
AppManualToolbox.openManualActivity(context, builder.build())
```

```kotlin
// if you want to open the built app manual activity as a welcome screen,
// have the builder like above and then call this in your launcher activity.

class MainActivity : Activity {
	override fun onCreate(savedInstanceState: Bundle?) {
		val builder = AppManualToolbox.buildAppManual()
		builder
			.set ... // various builder settings
			
		if(!AppManualToolbox.handleFirstLaunch(this, builder.build())) {
			// your activity's code
		}

		// it manages first launch setting on its own and if this is the first launch
		// then welcome screen with your app manual will open.
	}
}
```
