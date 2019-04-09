import json,datetime
from random import randint

from django.http import HttpResponseRedirect, HttpResponse
from django.shortcuts import render

from noteManage.sqlHelp import query
from noteManage import backup_sql
from pyecharts import Pie,Line,Grid


def login(request):
    if request.method == 'POST':
        username = request.POST['uAccount']
        password = request.POST['uPassword']
        user_sql= '''SELECT * FROM adminuser WHERE a_name="%s" AND a_password="%s" '''%(username,password)
        user_list = query(user_sql)
        if user_list:
            request.session['user_id'] = user_list[0][0]

            return render(request,'login.html',{"result":"true"})
        else:
            return render(request,'login.html',{"result":"false"})
    else:
        return render(request,'login.html')


def logout(request):
    user = request.session.get('user_id', False)
    if user:
        del request.session['user_id']
    return HttpResponseRedirect('/login')


def index(request):
    user = request.session.get('user_id', False)
    if user:
        return render(request, '../static/index.html')
    else:
        return HttpResponseRedirect('/login')


# def user_list(request):
#     user = request.session.get('user_id', False)
#     if user:
#         if request.method == 'POST':
#             post_data = json.loads(request.body)
#             # page_limit = request.POST['page_limit'] if request.POST['page_limit'] else 10
#             # page_count = request.POST['page_count'] if request.POST['page_count'] else 1
#             if 'page_limit' in post_data:
#                 page_limit = post_data['page_limit'] if post_data['page_limit'] else 10
#             else:
#                 page_limit = 10
#             if 'page_count' in post_data:
#                 page_count = post_data['page_count'] if post_data['page_count'] else 1
#             else:
#                 page_count = 1
#             select_sql = '''SELECT * FROM user LIMIT %s , %s''' % ((page_count-1)*page_limit, page_limit)
#             select_data = query(select_sql)
#
#             data_list = []
#             data_dict = {'id': "", "openid": "", "name": "", "isuse": "", "isuse_text": ""}
#             for data in select_data:
#
#                 data_dict['id'] = data[0]
#                 data_dict['openid'] = data[1]
#                 data_dict['name'] = data[2]
#                 data_dict['isuse_text'] = "禁用" if data[5] == 1 else "恢复"
#                 data_dict['isuse'] = data[5]
#                 data_list.append(data_dict.copy())
#
#             return HttpResponse(json.dumps(data_list, indent=2), content_type='application/json')
#         else:
#             return render(request, "User/user_list.html.bak")
#     else:
#         return HttpResponseRedirect('/login')


def user_list(request):
    user = request.session.get('user_id')
    if user:
        uid_where = ""
        if request.method == 'POST':
            page = json.loads(request.body)
            # print(page)
            if 'uid' in page :
                uid = page['uid']
                uid_where = "WHERE u_id = "+uid
                page_count = 1
                if page['uid'] == "":
                    page_count = 1
                    uid_where = ""
            else:
                page_count = page['curr']
            # print(page_count)
        else:
            page_count = 1
        page_limit = 10
        pagenu_sql = '''SELECT COUNT(u_id) FROM user %s'''%(uid_where)
        # print('pagenu_sql' + pagenu_sql)
        pagenu = query(pagenu_sql)[0][0]//10+1
        select_sql = '''SELECT * FROM user  %s  LIMIT %s , %s''' % (uid_where,(page_count - 1) * page_limit, page_limit)
        select_data = query(select_sql)
        data_list = []
        data_dict = {'id': "", "openid": "", "name": "", "isuse": "", "isuse_text": ""}
        for data in select_data:
            data_dict['id'] = data[0]
            data_dict['openid'] = data[1]
            data_dict['name'] = data[2]
            data_dict['isuse_text'] = "禁用" if data[5] == 1 else "恢复"
            data_dict['isuse'] = data[5]
            data_list.append(data_dict.copy())
        if request.method == 'POST':
            return HttpResponse(json.dumps({'data_list':data_list, 'pagenu': 1},indent=2),content_type="application/json")
        else:
            return render(request, "User/user_list.html", {'data_list': data_list, 'pagenu': pagenu})

    else:
        return HttpResponseRedirect('/login')


def user_editType(requet,u_id,end):
    user = requet.session.get('user_id')
    if user:
        if requet.method == 'POST':
            update_sql = '''UPDATE user SET u_isuse=1 WHERE u_id=%s'''%(u_id)
            dele_sql = '''DELETE FROM blacklist WHERE u_id=%s'''%(u_id)
            query(update_sql)
            query(dele_sql)
            return HttpResponse(json.dumps({"res": "true"},indent=2), content_type='application/json')
        else:
            return render(requet, "User/user_editType.html", {"u_id": u_id, "end": end})
    else:
        return HttpResponseRedirect('/login')


def user_detail(request,u_id):
    user = request.session.get('user_id')
    if user:
        notenum1_sql = '''SELECT COUNT(n_id) FROM note WHERE u_id =%s '''%(u_id)
        notenum1 = query(notenum1_sql)[0][0]
        notenum2_sql = '''SELECT COUNT(tn_id) FROM teamnote WHERE u_id=%s'''%(u_id)
        notenum2 = query(notenum2_sql)[0][0]
        studionum_sql = '''SELECT COUNT(tp_id) FROM teampanel WHERE u_id=%s '''%(u_id)
        studionum = query(studionum_sql)[0][0]
        notelist1_sql = '''SELECT n_id,n_place,n_time FROM note WHERE u_id=%s '''%(u_id)
        notelist1 = query(notelist1_sql)
        notelist1_list = []
        for list in notelist1:
            notelist1_dict = {"n_id": list[0], "n_place": list[1], "n_time": list[2]}
            notelist1_list.append(notelist1_dict)

        notelist2_sql = '''SELECT tn_id,tp_id, LEFT(tn_content,10) FROM teamnote WHERE u_id=%s '''%(u_id)
        notelist2 = query(notelist2_sql)
        notelist2_list = []
        for list in notelist2:
            tp_name_sql = '''SELECT tp_name FROM teampanel WHERE tp_id=%s'''%(list[1])
            notelist2_dict = {"tn_id": list[0],"tp_name": query(tp_name_sql)[0][0], "tn_content": list[2] if len(list[2])<8 else list[2]+r"..."}
            notelist2_list.append(notelist2_dict)

        studiolist_sql = '''SELECT tp_id, tp_name,tp_comment, tp_addr, tp_time FROM teampanel WHERE u_id=%s '''%(u_id)
        studiolist = query(studiolist_sql)
        studio_list = []
        for list in studiolist:
            studio_dict = {"tp_id": list[0], "tp_name": list[1], "tp_comment": list[2], "tp_addr": list[3], "tp_time": list[4]}
            studio_list.append(studio_dict)

        result_dict = {"notenum1": notenum1, "notenum2": notenum2, "studionum": studionum, "notelist1_list": notelist1_list,
                           "notelist2_list":notelist2_list, "studio_list": studio_list}
        return render(request, "User/user_detail.html", result_dict)

    else:
        return HttpResponseRedirect('/login')


def note_detail (request, id, type_id):
    user = request.session.get('user_id')
    if user:
        result_data = {}
        img_url = 'https://www.bcuvote.top/NoteWechat/upload/'
        if type_id == '1':
            n_id = id
            select_sql = ''' SELECT * FROM note WHERE n_id=%s'''%(n_id)
            select_data = query(select_sql)[0]
            result_data = {"content": select_data[2], "img1": select_data[5], "img2": select_data[6],
                           "img3": select_data[7], "img4": select_data[8], "video": select_data[9],
                           "audio": select_data[10], "img_url": img_url}
        elif type_id == '2':
            pass
        elif type_id == "3":
            tn_id = id
            select_sql = '''SELECT * FROM teamnote WHERE tn_id=%s'''%(tn_id)
            select_data = query(select_sql)[0]
            result_data = {"content": select_data[3], "img1": select_data[4], "img2": select_data[5],
                           "img3": select_data[6],"img4": select_data[7], "video": select_data[8],
                           "audio": select_data[9], "img_url": img_url}
        return render(request,'User/note_detail.html', result_data)
    else:
        return HttpResponseRedirect('/login')


def black_list(request):
    user = request.session.get('user_id')
    if user:
        uid_where = ""
        if request.method == 'POST':
            page = json.loads(request.body)
            if 'uid' in page:
                uid = page['uid']
                uid_where = "WHERE u_id = " + uid
                page_count = 1
                if page['uid'] == "":
                    page_count = 1
                    uid_where = ""
            else:
                page_count = page['curr']
                # print(page_count)
        else:
            page_count = 1
        page_limit = 10
        pagenu_sql = '''SELECT COUNT(u_id) FROM blacklist '''
        pagenu = query(pagenu_sql)[0][0] // 10 + 1
        select_sql = '''SELECT * FROM blacklist %s LIMIT %s , %s '''%(uid_where,(page_count - 1) * page_limit, page_limit)
        select_data = query(select_sql)
        black_dict = {"u_id": "", "start_time": "", "end_time": "", "reason": "", "nickname": ""}
        black_list = []
        for data in select_data:
            black_dict['u_id'] = data[0]
            black_dict['start_time'] = str(data[2])
            black_dict['end_time'] = str(data[3])
            black_dict['reason'] = data[4]
            black_dict['nickname'] = str(query('''SELECT nickname FROM user WHERE u_id=%s'''%(data[0]))[0][0])
            black_list.append(black_dict.copy())
        if request.method == 'POST':
            return HttpResponse(json.dumps({'black_list': black_list}, indent=2), content_type='application/json')
        else:
            return render(request, 'Blacklist/black_list.html', {"pagenu": pagenu})
    else:
        return HttpResponseRedirect('/login')


def black_add(request, u_id):
    user = request.session.get('user_id')
    if user:
        if request.method == 'POST':
            post_data = json.loads(request.body)
            insert_sql = '''INSERT INTO blacklist VALUES(%s,%s,"%s","%s",%s)'''%(u_id,post_data['time'],post_data['start'],
                                                                             post_data['end'],post_data['reason'])
            update_sql = '''UPDATE  user SET u_isuse=0 WHERE u_id = %s'''%(u_id)
            query(insert_sql)
            query(update_sql)
            return HttpResponse(json.dumps({"result": "true"}, indent=2), content_type='application/json')
        else:
            return render(request, 'Blacklist/black_add.html', {"u_id": u_id})
    else:
        return HttpResponseRedirect('/login')


def illegal_list(request):
    user = request.session.get('user_id')
    if user:
            select_sql = '''SELECT u_id, COUNT(n_id) FROM note WHERE is_illegal=3 GROUP BY u_id'''
            select_data = query(select_sql)
            illegal_list = []
            illegal_dict = {'u_id': "", "nickname": "", "num": "", "note_list": []}
            note_dict = {'n_id': '', 'content': '', 'time': '', 'place': ''}

            for data in select_data:
                illegal_dict['u_id'] = data[0]
                illegal_dict['num'] = data[1]
                illegal_dict['nickname'] = str(query('''SELECT nickname FROM user WHERE u_id=%s'''%(data[0]))[0][0])
                note_sql = '''SELECT n_id, LEFT(n_content,6) ,n_time,n_place FROM note WHERE u_id=%s AND is_illegal=3'''%(data[0])
                note_data = query(note_sql)
                note_list = []
                for data2 in note_data:
                    note_dict['n_id'] = data2[0]
                    note_dict['content'] = data2[1] if len(data2[1]) < 6 else data2[1]+"......"
                    note_dict['time'] = data2[2]
                    note_dict['place'] = data2[3]
                    note_list.append(note_dict.copy())
                illegal_dict['note_list'] = note_list
                illegal_list.append(illegal_dict.copy())

            return render(request, 'Blacklist/illegal_list.html', {'illegal_list': illegal_list})
    else:
        return HttpResponseRedirect('/login')


def illegal_note(request,n_id):
    user = request.session.get("user_id")
    if user:
        img_url = 'https://www.bcuvote.top/NoteWechat/upload/'
        select_sql = '''SELECT * FROM note WHERE n_id = %s'''%(n_id)
        select_data = query(select_sql)[0]
        result_data = {"content": select_data[2], "img1": select_data[5], "img2": select_data[6],
                       "img3": select_data[7], "img4": select_data[8], "video": select_data[9],
                       "audio": select_data[10], "img_url": img_url}
        return render(request, 'Blacklist/illegal_note.html', result_data)
    else:
        return HttpResponseRedirect('/login')


def user_del(request,u_id):
    user = request.session.get("user_id")
    if user:
        if request.method == 'POST':
            del_sql = "DELETE FROM user WHERE u_id = %s "%(u_id)
            query(del_sql)
            return HttpResponse(json.dumps({"res": "true"},indent=2), content_type='application/json')
        else:
            return render(request, 'User/user_del.html', {'u_id':u_id})
    else:
        return HttpResponseRedirect('/login')

def sql_backup(request):
    user = request.session.get("user_id")
    if user:
        if request.method == 'POST':
            db_ip = request.POST['db_ip']
            db_user = request.POST['db_user']
            db_passwd = request.POST['db_passwd']
            db_name = request.POST['db_name']
            backup_path = request.POST['backup_path']
            db_port = request.POST['db_port']

            try:
                bk = backup_sql.backup(db_ip,db_port,db_user,db_passwd,db_name,backup_path)
                bk.run_backup()
                return render(request, 'SqlBackup/sql_backup.html', {"result": "true"})
            except Exception as e:
                print(e)
                return render(request, 'SqlBackup/sql_backup.html', {"result": "false"})
            # return HttpResponse(json.dumps({"res": "true"}, indent=2), content_type='application/json')
        else:
            return render(request, 'SqlBackup/sql_backup.html')
    else:
        return HttpResponseRedirect('/login')

def illegal_note_del(request,n_id):
    user = request.session.get("user_id")
    if user:
        if request.method == 'POST':
            del_sql = "DELETE FROM notewechat.note WHERE n_id = %s "%(n_id)
            query(del_sql)
            return HttpResponse(json.dumps({"res": "true"},indent=2), content_type='application/json')
        else:
            return render(request, 'Blacklist/illegal_note_del.html', {'n_id':n_id})
    else:
        return HttpResponseRedirect('/login')

REMOTE_HOST = "https://pyecharts.github.io/assets/js"

def dashboard(request):
    user = request.session.get("user_id")
    if user:
        pie = note_kind_pie()
        grid = blacknote_num_Grid()

        context = dict(
            piechart = pie.render_embed(),
            grid = grid.render_embed(),
            host =REMOTE_HOST,
            script_list = set(pie.get_js_dependencies()) | set(grid.get_js_dependencies())
        )
        return render(request,'dashboard.html',context)
    else:
        return HttpResponseRedirect('/login')


def note_kind_pie():
    attr = ["图文","音频","视频"]
    pic_num = query("SELECT COUNT(*) FROM notewechat.note WHERE n_img1 IS NOT NULL OR n_img2 IS NOT NULL OR n_img3 IS NOT NULL OR n_img4 IS NOT NULL")[0]
    audio_num = query("SELECT COUNT(*) FROM notewechat.note WHERE n_audio IS NOT NULL")[0]
    video_num = query("SELECT COUNT(*) FROM notewechat.note WHERE n_video IS NOT NULL")[0]
    v1 = [pic_num,audio_num,video_num]
    # v1 = [11 , 12 , 13]
    pie = Pie("便签类型占比")
    pie.add("", attr, v1, is_label_show=True)
    return pie

def blacknote_num_Grid():
    month_arrt = list(reversed([(datetime.datetime.now() + datetime.timedelta(days=-x*30)).strftime("%Y-%m-%d") for x in range(0, 6)]))
    month_value = [query("SELECT COUNT(*) FROM notewechat.blacklist WHERE TO_DAYS('%s')-TO_DAYS(start_time) >=0"%x)[0][0] for x in month_arrt]
    week_arrt = list(reversed([(datetime.datetime.now() + datetime.timedelta(weeks=-x)).strftime("%Y-%m-%d") for x in range(0, 6)]))
    week_value = [query("SELECT COUNT(*) FROM notewechat.blacklist WHERE TO_DAYS('%s')-TO_DAYS(start_time) >=0"%x)[0][0] for x in week_arrt]
    day_arrt = list(reversed([(datetime.datetime.now() + datetime.timedelta(days=-x)).strftime("%Y-%m-%d") for x in range(1, 7)]))
    day_value = [query("SELECT COUNT(*) FROM notewechat.blacklist WHERE TO_DAYS('%s')-TO_DAYS(start_time) >=0"%x)[0][0] for x in day_arrt]

    line_1 = Line("违规便签数量变化")
    print(day_value)
    line_1.add("天", day_arrt, day_value ,legend_pos="30%",mark_line=["average"])

    line_2 = Line()
    line_2.add("周", week_arrt, week_value,legend_pos="center",mark_line=["average"])

    line_3 = Line()
    line_3.add("月", month_arrt, month_value, legend_pos="62%",mark_line=["average"])

    grid = Grid()
    grid.add(line_3,grid_top="60%",grid_left="60%")
    grid.add(line_2,grid_top="60%",grid_right="60%")
    grid.add(line_1,grid_bottom="60%")

    return grid

