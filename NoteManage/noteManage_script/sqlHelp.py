# -*- coding: UTF-8 -*-

from pymysql import connect


def printErr(s):
    print(s)

_con = ""

def query(sql):
    _res = []
    try:
        _con = connect(host="139.199.26.91", user="root", passwd="root", port=3306, db="notewechat", charset="utf8")
        try:
            cur = _con.cursor()
            cur.execute(sql)
            _res = cur.fetchall()
            _con.commit()
            cur.close()
            del cur
        except Exception as e:
            printErr(e)
        _con.close()
        return _res
    except Exception as e:
        print(e)
        return _res
