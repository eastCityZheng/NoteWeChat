// pages/BuildNote/BuildNote.js
var app = getApp()
var InsertNoteVideo_url = app.appServlet.servlet + "InsertNoteVideoServlet";
var UploadFile_url = app.appServlet.servlet + "UploadFileServlet";
var datetime = require('../../util/getDate.js');

Page({
  /**
   * 页面的初始数据
   */
  data: {
    src:'',
    colortype: 1,//颜色选择
    flag: true,
    seetype: 0,
    textareashow: false, //设置textarea弹出时消失
    imgtop: 70,          //改变距离
    Mylongitude: 0,     //精度 
    Mylatitude: 0,     //纬度
    /*hasLocation: false,*/
    adr: '',           //地址
    name: '',           //地址名
    n_content: '',
    n_page: "",
    tp_id: ""
  },
  changetextarea:function(e){
    this.setData({
      n_content: e.detail.value
    })
  },
  //完成
  changetoXX: function () {
    var that = this;
    if (that.data.n_page == "index") {
      wx.uploadFile({
        url: UploadFile_url,
        filePath: that.data.src,
        name: 'file',//这里根据自己的实际情况改
        formData: null,//这里是上传图片时一起上传的数据
        success: (resp) => {
          wx.request({
            url: InsertNoteVideo_url,
            data: {
              u_id: app.appuserinfo.u_id,
              n_content: that.data.n_content,
              n_time: datetime.formatTime(new Date()),
              n_place: that.data.name,
              n_color: that.data.colortype,
              n_power: that.data.seetype,
              n_video: resp.data,
              n_page: that.data.n_page
            },
            header: {
              "Content-Type": "json"
            },
            method: 'GET',
            success: function (res) {
              console.log(res.data)
              if (res.data == true) {
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
              } else {
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
              }
            },
            fail: function (res) { },
            complete: function (res) { },
          })
        },
        fail: (res) => {
        },
        complete: () => {
        }
      })
    }else{
      wx.uploadFile({
        url: UploadFile_url,
        filePath: that.data.src,
        name: 'file',//这里根据自己的实际情况改
        formData: null,//这里是上传图片时一起上传的数据
        success: (resp) => {
          wx.request({
            url: InsertNoteVideo_url,
            data: {
              u_id: app.appuserinfo.u_id,
              n_content: that.data.n_content,
              n_time: datetime.formatTime(new Date()),
              n_place: that.data.name,
              n_color: that.data.colortype,
              n_power: that.data.seetype,
              n_video: resp.data,
              n_page: that.data.n_page,
              tn_nickname: app.appuserinfo.username,
              tp_id: that.data.tp_id,
              tn_headimg: app.appuserinfo.headimg
            },
            header: {
              "Content-Type": "json"
            },
            method: 'GET',
            success: function (res) {
              console.log(res.data)
              if (res.data == true) {
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
              } else {
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
              }
            },
            fail: function (res) { },
            complete: function (res) { },
          })
        },
        fail: (res) => {
        },
        complete: () => {
        }
      })
    }
    


   
  },
  
  //选择颜色
  s1: function (e) {
    this.setData({
      colortype: 1
    })
  },
  s2: function (e) {
    this.setData({
      colortype: 2
    })
  },
  s3: function (e) {
    this.setData({
      colortype: 3
    })
  },
  s4: function (e) {
    this.setData({
      colortype: 4
    })
  },
  s5: function (e) {
    this.setData({
      colortype: 5
    })
  },
  s6: function (e) {
    this.setData({
      colortype: 6
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
    let testname = JSON.parse(options.extra);
    console.log(testname)
    this.setData({
      src: testname,
      n_page: options.page,
      tp_id: options.tp_id
    })
    console.log("tiao"+this.data.src)
  },
 
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.videoContext = wx.createVideoContext('myVideo')
  },
  //全屏
  quanp: function (e) {
    this.videoContext.requestFullScreen({
      direction:0
    })
  },
  //退出全屏
  exitfull:function(e){
    console.log(e)
    if(e.detail.fullScreen==false){
      this.videoContext.pause()
    }
    
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

