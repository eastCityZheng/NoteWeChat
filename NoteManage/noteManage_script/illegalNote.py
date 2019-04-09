# -*- coding: utf-8 -*-

from sqlHelp import query


def read_word():
    word_data = ""
    with open("word.txt","r",encoding='utf-8') as f:
        for line in f:
            word_data += line
        word_list = word_data.split(" ")
        word_list = [x.strip() for x in word_list if x != '']
    return word_list


def stop_count():
    count_sql = ''' SELECT MAX(n_id) FROM note'''
    return int(query(count_sql)[0][0])


def start_count():
    count_sql = ''' SELECT MIN(n_id) FROM note'''
    return int(query(count_sql)[0][0])


def findNote(word_list,ans):
    select_sql = '''SELECT * FROM note WHERE n_id = %s AND u_id IN (SELECT u_id FROM user WHERE u_isuse =1)'''%(ans)
    select_data = query(select_sql)
    if select_data :
        select_data = select_data[0]
        print(select_data)
        update_2 = '''UPDATE note SET is_illegal= 2 WHERE n_id =%s''' % (ans)
        update_3 = '''UPDATE note SET is_illegal= 3 WHERE n_id =%s''' % (ans)

        ans = query('''SELECT MIN(n_id) FROM note WHERE n_id>%s''' % (ans))[0][0]
        if ans == None:
            if select_data[14] != 1:
                return stop_count() +1
            for word in word_list:
                if word in select_data[2] :
                    query(update_3)
                    return stop_count() + 1
            query(update_2)
            return stop_count() + 1

        if select_data[14] != 1:
            return int(ans)
        else:
            for word in word_list:
                if word in select_data[2]:
                    query(update_3)
                    return int(ans)
        query(update_2)
        return int(ans)
    else:
        ans = query('''SELECT MIN(n_id) FROM note WHERE n_id>%s''' % (ans))[0][0]
        if ans:
            return int(ans)
        else:
            return stop_count() + 1

def back():
    back_sql = '''UPDATE note SET is_illegal=1'''
    query(back_sql)
    print("DONE")


if __name__ == "__main__":
    ans = start_count()
    while True:
        end = stop_count()
        if ans <= end:
            ans = findNote(read_word(), ans )

    # back()