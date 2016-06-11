# SocketSample
這次作業要我們練習在android 的app執行socket跟server。
總共大致上分為兩個部分：第一個是socket跟server的連接，另外一個是計算機的架構。

由於助教有給充分的資料，因此我直接使用助教給的"SocketSample"來做修改。
執行javaserver跟app裡面的main檔後，稍微測試一下，就發現可以連接上去了，
所以我直接在app裡面再多加計算機架構後，在使用client跟server互相溝通後，
就完成了作業。

一開始執行javaserver檔後，會產生一個window，顯示當下的ip，然後執行app後，
第一個layout就是要輸入ip，輸入正確的ip後，在server的window會顯示"Hi Client"
代表連接上了server。

然後再執行簡單的計算機的程式後，把結果的數字轉會成String，然後再傳回server，
server再用setText的方式，顯示到window上，使用者就可以透過server的window，
看到app上輸入的方程式跟答案，接著還可以選擇return，再輸入其他算式。

這次作業遇到了一些困難，像是在client端的onClient的method裡面，沒有寫好判斷式，
造成每按任意一個button後，都會再重新產生一個thread，使程式當機。
在client端傳回string後，卻在server端一直收不到回應，檢查後才發現，在server端的
run的method裡面，沒有寫一個無限迴圈來讀取client的資料，所以才會一直收不到。
另外還有一些小錯誤像是不小心把創建window的程式寫到run的method裡面，使得server一直當機，
或者是thread忘記初始化等等，也算是學到了許多經驗。