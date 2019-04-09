"""noteManage URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from noteManage import views
from django.conf.urls import url

urlpatterns = [
    path('admin/', admin.site.urls),
    url(r'^$', views.login),
    url(r'^login$', views.login),
    url(r'^index$', views.index),
    url(r'^logout$', views.logout),
    url(r'^user_list$', views.user_list),
    url(r'^user_editType/([1-9][0-9]*)/([0-9]{4}-[0-1][0-9]-[0-3][0-9])$',views.user_editType),
    url(r'^user_detail/([1-9][0-9]*)$', views.user_detail),
    url(r'^note_detail/(\d+)/([1-3])$', views.note_detail),
    url(r'^black_list$', views.black_list),
    url(r'^black_add/(\d+)$', views.black_add),
    url(r'^illegal_list$', views.illegal_list),
    url(r'^illegal_note/([1-9][0-9]*)$', views.illegal_note),
    url(r'^user_del/(\d+)$',views.user_del),
    url(r'^illegal_note_del/(\d+)$',views.illegal_note_del),
    url(r'^sql_backup$',views.sql_backup),
    url(r'^dashboard$',views.dashboard),
]