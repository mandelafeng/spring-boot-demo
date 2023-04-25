#! /bin/bash
# 环境准备
# 1. git
# 2. jdk
# 3. maven
# 4. redis
# 5. nginx
# 6. mysql
cd /opt/packages || exit
pwd


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

# 解压安装包
tar -zxvf git-2.32.0.tar.gz
tar -zxvf jdk-8u301-linux-x64.tar.gz
tar -zxvf apache-maven-3.8.2-bin.tar.gz
tar -zxvf mysql-5.7.35-linux-glibc2.12-x86_64.tar.gz
tar -zxvf redis-6.2.5.tar.gz
tar -zxvf nginx-1.20.1.tar.gz

# 检查Git是否已安装
git --version > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "Git已经安装！"
else
    # 安装Git
    cd git-2.32.0
    make configure
    ./configure --prefix=/usr/local
    make install
fi

# 检查JDK是否已安装
java -version > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "JDK已经安装！"
else
    # 安装JDK 8
    cd ../jdk1.8.0_301
    sudo mkdir /usr/java
    sudo mv * /usr/java
    sudo update-alternatives --install /usr/bin/java java /usr/java/bin/java 1
    sudo update-alternatives --install /usr/bin/javac javac /usr/java/bin/javac 1
fi

# 检查Maven是否已安装
mvn -version > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "Maven已经安装！"
else
    # 安装Maven
    cd ../apache-maven-3.8.2
    sudo mv * /usr/local/
    sudo echo "export M2_HOME=/usr/local/apache-maven-3.8.2" >> ~/.bashrc
    sudo echo "export PATH=\${M2_HOME}/bin:\${PATH}" >> ~/.bashrc
    source ~/.bashrc
fi

# 检查MySQL是否已安装
mysql --version > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "MySQL已经安装！"
else
    # 安装MySQL 5.7
    cd ../mysql-5.7.35-linux-glibc2.12-x86_64
    sudo groupadd mysql
    sudo useradd -r -g mysql mysql
    sudo mkdir -p /usr/local/mysql/data
    sudo mkdir /var/run/mysqld
    sudo chown -R mysql:mysql /usr/local/mysql
    sudo chown -R mysql:mysql /var/run/mysqld
    sudo cp support-files/mysql.server /etc/init.d/mysql
    sudo chmod +x /etc/init.d/mysql
    sudo update-rc.d mysql defaults
    sudo service mysql start
    sudo /usr/local/mysql/bin/mysql_secure_installation
fi

# 检查Redis是否已安装
redis-server --version > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "Redis已经安装！"
else
    # 安装Redis
    cd ../redis-6.2.5
    make
    sudo make PREFIX=/usr/local/redis install
fi

# 检查NGINX是否已安装
nginx -v > /dev/null 2>&1
if [ $? -eq 0 ]; then
    echo "NGINX已经安装！"
else
    # 安装NGINX
    cd ../nginx-1.20.1
    ./configure --prefix=/usr/local/nginx
    make
    sudo make install
fi

echo "所有软件安装完成！"





