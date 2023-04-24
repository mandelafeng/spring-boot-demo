#! /bin/bash
# 环境准备
# 1. git
# 2. jdk
# 3. maven
# 4. redis
# 5. nginx
# 6. mysql

# git
if git --version | grep 'git version [0-9]*\.[0-9]*\.[0-9]*'; then
  echo '已安装Git'
  else
    echo '需要安装git'
    tar -zxvf git-2.22.0.tar.gz
fi

