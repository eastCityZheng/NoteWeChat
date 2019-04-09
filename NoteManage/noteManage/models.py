from django.db import models


class Adminuser(models.Model):
    a_id = models.IntegerField(primary_key=True)
    a_name = models.CharField(max_length=45)
    a_password = models.CharField(max_length=45)
