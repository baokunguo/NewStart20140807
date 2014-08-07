2014年 08月 07日 星期四 21:30:55 CST

This is the read me file, you can get the information about this project.

It will change as the project goes.

this is the new branch ....

....

2.5 Git 基础 - 远程仓库的使用
远程仓库的使用
要参与任何一个 Git 项目的协作，必须要了解该如何管理远程仓库。远程仓库是指托管在网络上的项目仓库，可能会有好多个，其中有些你只能读，另外有些可以写。同他人协作开发某个项目时，需要管理这些远程仓库，以便推送或拉取数据，分享各自的工作进展。 管理远程仓库的工作，包括添加远程库，移除废弃的远程库，管理各式远程库分支，定义是否跟踪这些分支，等等。本节我们将详细讨论远程库的管理和使用。

查看当前的远程库

--------------

history 

2049  touch README.md
 2050  git init 
  2051  git add README.md
   2052  git commit -m "the comment Here"
    2053  git  remote add origin https://github.com/baokunguo/NewStart20140807.git
     2054  git push -u origin master
      2055  ls
       2056  ls -al
        2057  git add
         2058  git add .
          2059  vim README.md 
           2060  git status 
            2061  git commit -m "the begining of the project test"
             2062  git add .
              2063  git status 
               2064  git commit -m "change of files ReadMe"
                2065  git pull
                 2066  git add .
                  2067  git push 
                   2068  git status 

