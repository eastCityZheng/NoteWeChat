#!/usr/bin env python3
import os
import time
import datetime

class backup(object):
    def __init__(self,db_ip,db_port,db_user,db_passwd,db_name,backup_path):
        self.db_ip = db_ip
        self.db_user = db_user
        self.db_passwd = db_passwd
        self.db_name = db_name
        self.backup_path = backup_path
        self.db_port = db_port

    def exec_cmd(self,cmd):
        return os.system(cmd)

    def datetime(self):
        return time.strftime('%Y%m%d-%H%M%S', time.localtime(time.time()))

    #创建备份目录
    def createPath(self):
        if not os.path.exists(self.backup_path):
            os.makedirs(self.backup_path)
        print("create path done")

    def mysql_read(self,cmd):
        p = os.popen('''mysql -h%s -P%s -u%s -p%s -e "%s"''' % (self.db_ip,
                                                                self.db_port,
                                                                self.db_user,
                                                                self.db_passwd,
                                                                cmd))
        return p.readlines()

    #检查是否有此数据库
    def check_database(self):
        if self.exec_cmd('''mysql -h%s -P%s -u%s -p%s -e "use %s"''' % (self.db_ip,
                                                                self.db_port,
                                                                self.db_user,
                                                                self.db_passwd,
                                                                self.db_name)):
            print('''mysql don't have database that named %s'''%self.db_name)
            # return '''mysql don't have database that named %s'''%self.db_name

    def run_backup(self):
        self.createPath()
        self.check_database()
        sql_cmd = "mysqldump -h%s -P%s -u%s -p%s %s > %s/notewechat_%s.sql"%(self.db_ip,
                                                                             self.db_port,
                                                                             self.db_user,
                                                                             self.db_passwd,
                                                                             self.db_name,
                                                                             self.backup_path,
                                                                             self.datetime())
        self.exec_cmd(sql_cmd)
        print("backup sql done")




