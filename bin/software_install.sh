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

# 判断当前系统是Ubuntu还是centos
if [ -f /etc/lsb-release ]; then
    . /etc/lsb-release
    OS=$DISTRIB_ID
elif [ -f /etc/debian_version ]; then
    OS=Debian
elif [ -f /etc/redhat-release ]; then
    OS="`cat /etc/redhat-release | sed s/.*release\ // | sed s/\ .*//`"
else
    OS="$(uname -s) $(uname -r)"
fi
case $OS in
  "Ubuntu")
    echo "This is Ubuntu"
    ;;
  "CentOS")
    echo "This is CentOS"
    ;;
  *)
    echo "This is neither Ubuntu nor CentOS"
    ;;
esac

