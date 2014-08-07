2014年 08月 07日 星期四 21:30:55 CST

This is the read me file, you can get the information about this project.

It will change as the project goes.

----------------------------------------------
1.Git Usage:
    git --help blog ===> if you forget the usage of git
    git config --list 查看配置信息
    a.新建仓库：
        在本地目录下初始化仓库,进入本地目录，touch README.md
        git init
        git add *
        git commit -m 'initial ....'
        git remote add origin ......git
        git push -u origin master
        或者从现有仓库克隆
        git remote add origin .......git_address
        git push -u origin master
        复制到本地
        git clone git_address

    b.分支
        当你有什么内容需要添加，但又不确定的时候，或者需要创建项目的另一个独立功能时，你可以创建新的分支，独立于主分支，默认地每次提交的代码都更新到master主分支上。
        创建并同时切换到新建分支上：git checkout -b branch_name
        也可以先创建一个分支再切换到新分支上：
        git branch branch_name
        git checkout branch_name
        查看该项目的所有分支
        git branch
        注意：任何时候你都可以通过git checkout master切换到主分支上，而且你可以有多个分支，也可以在一个分支上再新建分支。
        
        合并分支：
        在你的新分支，你完成任务后用git add添加你新增的文件
        然后
        git add .git
        git commit -m "add new feature" 
        git checkout master ##切换到主分支
        git merge branch_name   ##将新分支的内容合并到主分支上，主分支和新分支就一样了

        删除分支：
        如果你觉得新分支没有用了，也不想保存了
        在主分支上
        git branch -d branch_name   ##删除已经合并到主分支的branch
        git branch -D branch_name   ##删除branch分支，不管有没合并
        
    c.回到项目之前提交的版本（很实用）
        查看提交记录：
        git log
        输出历史记录，像下面这样子：
        commit ca82a6dff817ec66f44342007202690a93763949Author: your_username your_email@domain.comDate:   Mon Nov 4 12:52:11 2013 -0700    changes the frontpage layout
        commit 085bb3bcb608e1e8451d4b2432f8ecbe6306e7e7Author: your_username your_email@domain.comDate:   Mon Nov 4 11:40:33 2013 -0700    adds my new feature
        commit a11bef06a3f659402fe7563abf99ad00de2209e6Author: your_username your_email@domain.comDate:   Mon Nov 4 10:37:28 2013 -0700    initial commit
        如果想回到" adds my new feature" 这个版本，简单地用提交的ID就可以了，ID的前几位（可以区分其他版本就可以了）
        git checkout 085bb3bcb
        或者可以把这个版本变为一个新的独立分支：
        git checkout -b brach_name 085bb3bcb

    e.提交代码到远程代码库
        git remote add origin git@github.com:username/project_name.git  ## 即刚刚创建的仓库的地址
        git push -u origin master   ##推送代码到远程代码库

        转自:http://xie2010.blog.163.com/blog/static/21131736520140583731295/
        参考：git中文文档
        http://git-scm.com/book/zh/%E8%B5%B7%E6%AD%A5
