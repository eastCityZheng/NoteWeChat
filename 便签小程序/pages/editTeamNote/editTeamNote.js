// pages/BuildNote/BuildNote.js
var app = getApp()

var UpdateTeamNote_url = app.appServlet.servlet + "UpdateTeamNoteServlet";
var UpdateTNWithoutSrc_url = app.appServlet.servlet + "UpdateTNWithoutSrcServlet";
var UploadFile_url = app.appServlet.servlet + "UploadFileServlet";
var GetOneTN_url = app.appServlet.servlet + "GetOneTNServlet";

var datetime = require('../../util/getDate.js');

Page({
  /**
   * 页面的初始数据
   */
  data: {

    name2: "",
    author: '',
    poster: "",
    src: '',
    audiourl: app.appServlet.servlet + "upload/",
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
    filename: [],      //图片文件名
    texta: "",
    tn_id: '',
    n_note: null
  },
  textachange: function (e) {
    this.setData({
      texta: e.detail.value
    })
  },
  //完成
  changetoXX: function () {
    var that = this;
    if (that.data.files[0]) {
      //图片标签
      var newfiles = [];
      var oldfilesname = [];
      var lik = app.appServlet.servlet + "upload/"
      for (let i in that.data.files) {
        if (that.data.files[i].indexOf(lik)) {
          newfiles.push(that.data.files[i])
        } else
          oldfilesname.push(that.data.files[i].slice(lik.length))
      }

      that.uploadimg({
        url: UploadFile_url,//这里是你图片上传的接口
        path: that.data.files,//这里是选取的图片的地址数组
        oldfilesname: oldfilesname,   //老的图片文件名
        newfiles: newfiles    //新的图片链接
      });
    } else {
      //视频以及音频
      wx.request({
        url: UpdateTNWithoutSrc_url,
        data: {
          tn_id: that.data.tn_id,
          u_id: app.appuserinfo.u_id,
          n_content: that.data.texta,
          n_time: datetime.formatTime(new Date()),
          n_color: that.data.colortype,
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
              title: '修改失败！',
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
              title: '修改成功！',
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
    }

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
        console.log(res)
        that.setData({
          /*hasLocation: true,*/
          Mylongitude: res.longitude,
          Mylatitude: res.latitude
        })
      },
    })
    that.setData({
      tn_id: options.tn_id
    })

    this.videoContext = wx.createVideoContext('myVideo')
    this.audioCtx = wx.createAudioContext('myAudio')
    that.setData({
      author: app.appuserinfo.username,
      poster: app.appuserinfo.headimg
    })

    wx.request({
      url: GetOneTN_url,
      data: {
        tn_id: that.data.tn_id
      },
      header: {
        "Content-Type": "json"
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data)
        if (res.data.note['0'].n_img1 != '')
          that.data.files[0] = app.appServlet.servlet + "upload/" + res.data.note['0'].n_img1
        if (res.data.note['0'].n_img2 != '')
          that.data.files[1] = app.appServlet.servlet + "upload/" + res.data.note['0'].n_img2
        if (res.data.note['0'].n_img3 != '')
          that.data.files[2] = app.appServlet.servlet + "upload/" + res.data.note['0'].n_img3
        if (res.data.note['0'].n_img4 != '')
          that.data.files[3] = app.appServlet.servlet + "upload/" + res.data.note['0'].n_img4
        that.setData({
          n_note: res.data.note['0'],
          files: that.data.files,
          colortype: res.data.note['0'].n_color,
          name: res.data.note['0'].n_place,
          texta: res.data.note['0'].n_content
        })

        console.log(that.data.files[0])
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },

  //多张图片上传
  uploadimg: function (data) {
    var that = this,
      i = data.i ? data.i : 0  //当前上传的哪张图片
    wx.uploadFile({
      url: data.url,
      filePath: data.newfiles[i],
      name: 'file',//这里根据自己的实际情况改
      formData: null,//这里是上传图片时一起上传的数据
      success: (resp) => {
        data.oldfilesname.push(resp.data)
      },
      fail: (res) => {
      },
      complete: () => {
        i++;
        if (i >= data.newfiles.length) {   //当图片传完时，停止调用
          wx.request({
            url: UpdateTeamNote_url,
            data: {
              tn_id: that.data.tn_id,
              u_id: app.appuserinfo.u_id,
              n_content: that.data.texta,
              n_time: datetime.formatTime(new Date()),
              n_color: that.data.colortype,
              n_img: data.oldfilesname,
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
                  title: '修改失败！',
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
                  title: '修改成功！',
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
          console.log('执行完毕');
        } else {//若图片还没有传完，则继续调用函数
          console.log(i);
          data.i = i;
          that.uploadimg(data);
        }

      }
    });
  },
  //全屏
  quanp: function (e) {
    this.videoContext.requestFullScreen({
      direction: 0
    })
  },
  //退出全屏
  exitfull: function (e) {
    console.log(e)
    if (e.detail.fullScreen == false) {
      this.videoContext.pause()
    }

  },
  //控制 音频播放
  startandpause: function () {
    if (this.data.audiostate) {
      this.audioCtx.pause();
      this.setData({
        audiostate: false
      })
    } else {
      this.audioCtx.play();
      this.setData({
        audiostate: true
      })
    }
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

