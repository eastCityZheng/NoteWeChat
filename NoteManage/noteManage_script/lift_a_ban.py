# -*- coding: utf-8 -*-

from sqlHelp import query
import datetime

def strtotime(end_time):
    return datetime.datetime.strptime(end_time, "%Y-%m-%d")

def currtime():
    return datetime.datetime.now()


if __name__ == "__main__":
    while True:
        sql = "SELECT * FROM notewechat.blacklist "
        blacklist_con = query(sql)
        for content in blacklist_con:
            if strtotime(str(content[3]))<currtime():
                query("DELETE FROM notewechat.blacklist WHERE u_id =%s"%content[0])
                query("UPDATE  notewechat.user SET u_isuse =1 WHERE u_id =%s"%content[0])



