### Android news app

Mobile app where you can check the latest worldwide world news using [NewsAPI](https://newsapi.org). First time I use an API in a mobile app. I followed a year old guide to 
create this project but I still needed to look a lot of stuff up because some of the methods were deprecated or just didn't work anymore and I also had to fix my own bugs. In this app
you can get the latest news, search news by keywords and save the article to your saved news. I use Room as a database and the project is done in the MVVM architecture. Most of the
were new to me so I learned a ton doing this project, including RecyclerView, ViewBinding, API calls, Fragments, ViewModel, etc. Some concepts I still need to practice.

This is the starting screen, where the breaking get loaded.

![](/assets/startscreen.png)

You can search news by keyword.

![](/assets/searchnews.png)

You click on an article you get send to the web view to read it.

![](/assets/webview.png)

You can click the heart button to save the article to saved news.

![](/assets/hitsavebutton.png)

The saved articles are saved in the saved news tab.

![](/assets/savednews.png)

You can swipe left to delete the article from saved news.

![](/assets/swipeleft.png)

You get the option to undo the article deleting.

![](/assets/undomessage.png)




