// pages/record1/record1.js
var app = getApp()
var interval;
var varName;
var ctx = wx.createCanvasContext('canvasArcCir');

var StartAudio_url = app.appServlet.servlet + 'StartAudioServlet';

//倒计时
function countdown(that) {
  var second = that.data.second
  var typet = that.data.type
  var type1=that.data.type1
  if (second == 60) {
    that.setData({
      second: 60
    });
    return;
  }
  if (type1 == 1) {
    that.setData({
      second: 0
    });
    return;
  }
  if (typet == 1&&type1==0) {
    that.setData({
      second: second
    });

    return;
  }
  var time = setTimeout(function () {
    that.setData({ second: second + 1 });
    countdown(that);
  }
    , 1000)
}
Page({
  /**
   * 页面的初始数据
   */
  data: {
    type1:0,
    type: 1,  //1未点击，0点击
    le: -1,
    second: 0, //倒计时数
    flag1: true,//弹窗
    flag2: true,
    screenWidth: wx.getSystemInfoSync().windowWidth,
    screenHeight: wx.getSystemInfoSync().windowHeight,
    cr:0,
    cy:0,
    Timestart: null,
    TimeIng: 0,
    tempFilePath: '',
    n_page: '',
    tp_id: ''       //便签文本
  },
  /**
  * 生命周期函数--监听页面加载
  */
  onLoad: function (options) {
    var that=this;
    var recorderManager = wx.getRecorderManager();
    //录音出错
    recorderManager.onError((res) => {
      console.log('小伙砸你录音失败了！')
    })
    console.log(options)
    that.setData({
      n_page: options.page,
      tp_id: options.tp_id
    })
  },

  //绘制背景的灰色圆
  drawbg: function () {
    //创建并返回绘图上下文context对象。
    var cxt_arc = wx.createCanvasContext('canvasCircle');
    cxt_arc.setLineWidth(6);
    cxt_arc.setStrokeStyle('#dcdcdc');
    cxt_arc.setLineCap('round');
    cxt_arc.beginPath();
    var r = (81 / 603 * this.data.screenHeight);
    /*var x = (this.data.screenWidth / 2) - (r + 12);
    var y = (77.5 + 6 + r);*/
    var y = (173 / 603 * this.data.screenHeight);
    var x = this.data.screenWidth/2;
    cxt_arc.arc(x, y, r, 0, 2 * Math.PI, false);
    cxt_arc.stroke();
    cxt_arc.draw();
    console.log()
    console.log(this.data.screenWidth / 8)
    console.log("r"+r)
    console.log('屏幕高度:' + this.data.screenHeight)
    console.log('屏幕宽度:' + this.data.screenWidth)
    console.log("y" + y)
    console.log("2y" + 2 * y)
    var that = this
    this.setData({
      cr: r,
      cy:y,
    });

  },
  //绘制蓝色计时圆
  drawCircle: function () {
    ctx.clearRect(0, 0, 800, 800);
    ctx.draw();
    // clearInterval(varName);
    function drawArc(x, y, s, e, r) {
      ctx.draw();
      ctx.setLineWidth(5);
      ctx.setStrokeStyle('#4a8fff');
      ctx.setLineCap('round');
      ctx.beginPath();
      ctx.arc(x, y, r, s, e, false);
      ctx.stroke()
      ctx.draw()
    }
    var step = 2, startAngle = 1.5 * Math.PI, endAngle = 0,
    r = (81 / 603 * this.data.screenHeight);
    var animation_interval = 1000, n = 60;
    var y = (173 / 603 * this.data.screenHeight);
    var x = this.data.screenWidth / 2;
    var animation = function () {
      if (step <= n) {
        endAngle = step * 2 * Math.PI / n + 1.5 * Math.PI;
        drawArc(x, y, startAngle, endAngle, r);
        step++;
      } else {
        clearInterval(varName);
      }
    };
    varName = setInterval(animation, animation_interval);
  },


  //开始录音_changeimg
  Startrecord: function (e) {
    //圆
    var that = this;
    var type = that.data.type == 1 ? 0 : 1;
    that.setData({
      type: type,
      type1:0
    });
    if (type == 0) {
      this.data.second = 0;
      countdown(this);
      this.drawCircle();
      //录音
      var Timestart = e.timeStamp
      var recorderManager = wx.getRecorderManager();
      const options = {
        duration: Timestart,
        duration: 120000,
        sampleRate: 16000,
        numberOfChannels: 1,
        encodeBitRate: 96000,
        format: 'mp3',
        frameSize: 50
      }
      recorderManager.start(options)  
    } else {
      //停止画圆
      clearInterval(varName);
      //停止录音
      var recorderManager = wx.getRecorderManager();//获取全局唯一的录音管理器  
      var Timestart = this.data.Timestart;
     // var Timeout = e.timeStamp;
      //var TimeIng = 0;//录音的时长  
      //TimeIng = Timeout - Timestart;
     // this.setData({ TimeIng: TimeIng })
      //console.log(TimeIng)
      recorderManager.stop();
      //录音停止
      recorderManager.onStop((res) => {
        var tempFilePath = res.tempFilePath;// 文件临时路径  
        // var temp = tempFilePath.replace('.mp3', '')
        this.setData({ tempFilePath: tempFilePath })
        console.log(tempFilePath)
        // console.log(temp)
        console.log('劳资获取到文件了，开始上传')
        this.innerAudioContext = wx.createInnerAudioContext()
        this.innerAudioContext.src = tempFilePath;
        this.innerAudioContext.play();
      })
    }
    console.log(type)
    that.setData({
      Timestart: Timestart
    });
  },
  //弹出层show1
  show1: function () {
    var that = this;
    clearInterval(varName);
    var type1 = that.data.type1 == 1 ? 0 : 1;
    //清除画布像素
    ctx.clearRect(0, 0, 800, 800);
    ctx.draw();
    this.setData({
      flag1: false,
      type1: type1,
      type:1,
      second: 0,
    })
    console.log("type1"+type1)
    this.data.second = 0;
    //停止录音
    var recorderManager = wx.getRecorderManager();//获取全局唯一的录音管理器  
    var Timestart = this.data.Timestart;
    recorderManager.stop();
    //录音停止
    recorderManager.onStop((res) => {
      var tempFilePath = res.tempFilePath;// 文件临时路径  
      // var temp = tempFilePath.replace('.mp3', '')
      this.setData({ tempFilePath: tempFilePath })
      console.log(tempFilePath)
      // console.log(temp)
      console.log('劳资获取到文件了，开始上传')
      this.innerAudioContext = wx.createInnerAudioContext()
      this.innerAudioContext.src = tempFilePath;
      this.innerAudioContext.play();
    })
  },
  //弹出层show2
  show2: function () {
    var that = this;
    clearInterval(varName);
    ctx.clearRect(0, 0, 800, 800);
    ctx.draw();
    that.setData({
      flag2: false,
      second: 0
    })
    //停止录音
    var recorderManager = wx.getRecorderManager();//获取全局唯一的录音管理器  
    var Timestart = this.data.Timestart;
    recorderManager.stop();
    //录音停止
    recorderManager.onStop((res) => {
      var tempFilePath = res.tempFilePath;// 文件临时路径  
      // var temp = tempFilePath.replace('.mp3', '')
      this.setData({ tempFilePath: tempFilePath })
      console.log(tempFilePath)
      // console.log(temp)
      console.log('劳资获取到文件了，开始上传')
      wx.uploadFile({
        url: StartAudio_url,
        filePath: that.data.tempFilePath,
        name: "audio",
        header: {
          contentType: "multipart/form-data",//按需求增加  
        },
        formData: {
        },
        success: function (res) {
          console.log("zz" + res.data)
          wx.redirectTo({
            url: '../BuildNote3_record/BuildNote3_record?extra=' + res.data.replace("=", "_") +
            "&&page=" + that.data.n_page + "&&tp_id=" + that.data.tp_id,
          })
        }
      })
    })
    wx.uploadFile({
    url: StartAudio_url,
      filePath: that.data.tempFilePath,
      name: "audio",
      header: {
        contentType: "multipart/form-data",//按需求增加  
      },
      formData: {
      },

      success: function (res) {
        console.log("zz"+res.data)
        wx.redirectTo({
          url: '../BuildNote3_record/BuildNote3_record?extra=' + res.data.replace("=","_")+
          "&&page="+that.data.n_page+"&&tp_id="+that.data.tp_id,
        })
      }
    })
   
  },
  //hide1
  hide1: function () {
    this.setData({ flag1: true })
  },
  //hide2
  hide2: function () {
    this.setData({ flag2: true })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    //开始渲染灰圆
    this.drawbg()
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