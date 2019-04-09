// pages/BuildNote/BuildNote.js
var app = getApp()

var InsertNote_url = app.appServlet.servlet + "InsertNoteServlet";
var UploadFile_url = app.appServlet.servlet + "UploadFileServlet";


var datetime = require('../../util/getDate.js');

Page({
  /**
   * 页面的初始数据
   */
  data: {
    files: [],
    colortype: 0,//颜色选择
    flag: true,
    seetype: 0,
    textareashow: false, //设置textarea弹出时消失
    imgtop: 70,          //改变距离
    Mylongitude: 0,     //精度 
    Mylatitude: 0,     //纬度
    /*hasLocation: false,*/
    adr: '',           //地址
    name: '',           //地址名
    filename:[],      //图片文件名
    texta:"",
    n_page:'',
    tp_id:''       //便签文本
  },
  textachange:function(e){
      this.setData({
        texta:e.detail.value
      })
  },
  //完成
  changetoXX: function () {
    var that=this;
    console.log(this.data.filename)

    that.uploadimg({
      url: UploadFile_url,//这里是你图片上传的接口
      path: that.data.files//这里是选取的图片的地址数组
    });
   
  },
  //照片上传
  chooseImage: function (e) {
    var that = this;
    var imgs = this.data.files;
    wx.chooseImage({
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths;
        // console.log(res.tempFilePaths[0].slice(11));
        var files = that.data.files;
        for (var i = 0; i < tempFilePaths.length; i++) {
          if (files.length >= 4) {
            that.setData({
              files: files
            });
            return false;
          } else {
            files.push(tempFilePaths[i]);
            // that.data.filename.push(res.tempFilePaths[0].slice(11))
          }
        }
        // console.log(imgs);
        that.setData({
          files: files
        });

      }
    })
  },
  previewImage: function (e) {
    var index = e.currentTarget.dataset.index;
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.files // 需要预览的图片http链接列表
    })
    console.log(e.currentTarget.id)
  },
  // 删除图片
  deleteImg: function (e) {
    var files = this.data.files;
    var index = e.currentTarget.dataset.index;
    console.log(e.currentTarget.dataset.index)
    files.splice(index, 1);
    this.setData({
      files: files
    });
  },

  //选择颜色
  s1: function (e) {
    this.setData({
      colortype: 0
    })
  },
  s2: function (e) {
    this.setData({
      colortype: 1
    })
  },
  s3: function (e) {
    this.setData({
      colortype: 2
    })
  },
  s4: function (e) {
    this.setData({
      colortype: 3
    })
  },
  s5: function (e) {
    this.setData({
      colortype: 4
    })
  },
  s6: function (e) {
    this.setData({
      colortype: 5
    })
  },
  //弹出层show
  show: function () {
    var that = this;
    this.setData({
      flag: false,
      textareashow: true,
      imgtop: 440,
    })
  },
  //hide
  hide: function () {
    this.setData({
      flag: true,
      textareashow: false,
      imgtop: 70,
    })
  },
  //可见选择
  only: function () {
    this.setData({
      seetype: 0
    })
  },
  saoma: function () {
    this.setData({
      seetype: 1
    })
  },
  //地图跳转
  changetoMap: function () {
    var that = this
    wx.chooseLocation({
      success: function (res) {
        // success
        console.log(res)
        that.setData({
          hasLocation: true,
          adr: res.address,
          name: res.name
        })
      },
      fail: function () {
        // fail
      },
      complete: function () {
        // complete
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    //获取经纬度
    wx.getLocation({
      success: function (res) {
        that.setData({
          /*hasLocation: true,*/
          Mylongitude: res.longitude,
          Mylatitude: res.latitude
        })
      },
    })
    that.setData({
      n_page:options.page,
      tp_id:options.tp_id
    })
  },

  //多张图片上传
   uploadimg:function(data){
    var that= this,
    i = data.i ? data.i : 0  //当前上传的哪张图片
    wx.uploadFile({
      url: data.url,
      filePath: data.path[i],
      name: 'file',//这里根据自己的实际情况改
      formData: null,//这里是上传图片时一起上传的数据
      success: (resp) => {
        that.data.filename.push(resp.data)

      },
      fail: (res) => {
      },
      complete: () => {
        i++;
        if (i >= data.path.length) {   //当图片传完时，停止调用
         
          if (this.data.n_page == "index") {
            wx.request({
              url: InsertNote_url,
              data: {
                u_id: app.appuserinfo.u_id,
                n_content: that.data.texta,
                n_time: datetime.formatTime(new Date()),
                n_place: that.data.name,
                n_color: that.data.colortype,
                n_power: that.data.seetype,
                n_img: that.data.filename,
                n_page: that.data.n_page,
                lon: that.data.Mylongitude,
                lat: that.data.Mylatitude
              },
              header: {
                "Content-Type": "json"
              },
              method: 'GET',
              dataType: 'json',
              success: function (res) {
                console.log(res.data)
                if (res.data == false) {
                  wx.showToast({
                    title: '创建失败！',
                    icon: 'none',
                    duration: 1000
                  })
                  setTimeout(function () {
                    wx.navigateBack({
                      url: '../index/index'
                    })
                  }, 1200)
                } else {
                    wx.showToast({
                      title: '创建成功！',
                      icon: 'success',
                      duration: 1000
                    })
                    setTimeout(function () {
                      wx.navigateBack({
                        url: '../index/index'
                      })
                    }, 1200)
                  }
              },
              fail: function (res) { },
              complete: function (res) { },
            })
          } else {

            wx.request({
              url: InsertNote_url,
              data: {
                u_id: app.appuserinfo.u_id,
                n_content: that.data.texta,
                n_time: datetime.formatTime(new Date()),
                n_place: that.data.name,
                n_color: that.data.colortype,
                n_power: that.data.seetype,
                n_img: that.data.filename,
                n_page: that.data.n_page,
                tn_nickname: app.appuserinfo.username,
                tp_id: that.data.tp_id,
                tn_headimg: app.appuserinfo.headimg,
                
              },
              header: {
                "Content-Type": "json"
              },
              method: 'GET',
              dataType: 'json',
              success: function (res) {
                console.log(res.data)
                if (res.data == false) {
                  wx.showToast({
                    title: '创建失败！',
                    icon: 'none',
                    duration: 1000
                  })
                  setTimeout(function () {
                    wx.navigateBack({
                      url: '../gzmb/gzmb?tp_id=' + that.data.tp_id
                    })
                  }, 1200)
                } else {
                    wx.showToast({
                      title: '创建成功！',
                      icon: 'success',
                      duration: 1000
                    })
                    setTimeout(function () {
                      wx.navigateBack({
                        url: '../gzmb/gzmb?tp_id=' + that.data.tp_id
                      })
                    }, 1200)
                }
              },
              fail: function (res) { },
              complete: function (res) { },
            })
          }
          console.log('执行完毕');
        } else {//若图片还没有传完，则继续调用函数
          console.log(i);
          data.i = i;
          that.uploadimg(data);
        }

      }
    });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})

