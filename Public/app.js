// 图片加载时回调
var imgLoad = (function(){
	return function(url, callback, errorCb){
		var img = new Image();
		img.src = url;
		if (img.complete) {
            callback.call(img);
            return;
        };
        img.onload = function () {
        	callback.call(this);
            img = img.onload = img.onerror = null;
        };
        img.onerror = errorCb || function(){};
	}
})();

function is_weixin(){
    var ua = navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i)=="micromessenger") {
        return true;
    } else {
        return false;
    }
}

function preload(url) {
  var image = new Image();
  image.src = url;
}

function multi_swipe(){
	console.log('test');
}

function getSrcGlobal(obj){
	return obj.css('background-image').replace(/^url\(|\)$/g, '');
}

function LoadMp3Callback(){

}

var lotteryEnd = false;

	function getSrc(obj){
		return obj.css('background-image').replace(/^url\(|\)$/g, '');
	}

	function initLottery(){
		if(config.coverUrl){

		}else{
			failLottery();
		}
	}

	// 清遮罩，载图
	function loadImg(){
		$('#swipe li').each(function(i){
			if(i == config.swipeCur) return;

			var src = getSrc($(this).find('div')),
				img = new Image(),
				_this = this;

			if(src == 'none'){ return; }

			img.src = src;

			if($(this).data('role') == 'blur'){
				img.onload = function(){
					$(_this).stackBlur({img: img, radius: 10, callback: function(){
						$(_this).data('role', '');
					}});
				}				
			}
		});
	}

	function failLottery(){
		loadImg();
		initSwipe();
		$('#lottery').hide();
	}

	function initSwipe(){
      if(!(typeof old_ygj_info == 'undefined')){
	    var footer_url   = decodeURIComponent(old_ygj_info.footer_url);
	    var footer_title =decodeURIComponent(old_ygj_info.footer_title);  
           if(footer_title=='ygj_title_set_0R1sL63e'){
            	var total_html='';
	    	    html='<li><div class="top" style="background-image: url(/Public/Image/hb/global/copyright.jpg);background-size: 100% 100%;"><a href="javascript:void(0);" class="cop_bottom_guide"></a></div>';
	    	    html+='<div class="cop_bottom " style="height:0px;">';
	    	    html+='<div class="cop_beijin" style="display:none"></div>';
	    	    html+='<div class="cop_show" >';
	    	    html+='<div class="cop_title"></div>';
	    	    html+='<a href="http://mp.weixin.qq.com/s?__biz=MjM5NjY4MjYyMA==&mid=200827358&idx=1&sn=60365a79ae391a82f3c97c82e6d2d749#rd " class="cop_link"></a>';
	    	    html+='<a href="tel:05925195928" class="cop_tel"></a>';
	    	    html+='</div></div>';
                html+= '</li>';
                total_html += html;
                $('#swipe li').last().after(total_html);
            }else if(footer_title){
	    $('#swipe li').last().append('<div style="padding-top:10px;padding-bottom:10px;position:absolute;bottom:0;left:0;background-color:#51c886;width:100%;height:54px;font-size:24px;text-align:center;z-index:9999;font-family:microsoft yahei;"><a style="color:white;" href="'+footer_url+'"> '+ footer_title + '</a></div>');
	   }
	 }
	$('#swipe li').last().attr('id','ilast');

		// 先初始化4张
		var one = $('#swipe li').eq(0).find('.li_bg').eq(0);
		var two = $('#swipe li').eq(1).find('.li_bg').eq(0);
		var three = $('#swipe li').eq(2).find('.li_bg').eq(0);
		var four = $('#swipe li').eq(3).find('.li_bg').eq(0);
		preload(one.data('bginfo'));
		one.css( { "background-image" : 'url(' + one.data('bginfo') + ')' } );
		one.css( { "background-size"  : '100% 100%' } );
		if(two.length){
			preload(two.data('bginfo'));
		    two.css( { "background-image" : 'url(' + two.data('bginfo') + ')' } );
		    two.css( { "background-size"  : '100% 100%' } );
		}
		if(three.length){
			preload(three.data('bginfo'));
		    three.css( { "background-image" : 'url(' + three.data('bginfo') + ')' } );
		    three.css( { "background-size"  : '100% 100%' } );
		}
		if(four.length){
			preload(four.data('bginfo'));
		    four.css( { "background-image" : 'url(' + four.data('bginfo') + ')' } );
		    four.css( { "background-size"  : '100% 100%' } );
		}
		// End
	// if(!is_weixin()){
		if(config.enable_music){
			audioPlayer._audio.play();
		}

		if(!config.enable_music){
			audioPlayer.isPlay = false;
			$(audioPlayer.el).removeClass('on');
		}
	// }

		var isInitSwipe = $('#swipe li').length > 1;

		if(isInitSwipe){
			$('#swipe').swipe({
				cur: config.swipeCur,
				dir: config.swipeDir,
				success: function(){
					$(this).find('li').eq(config.swipeCur).removeAttr('style');
					// $('.f-hide').removeClass('f-hide');
					initPage(isInitSwipe);
				}
			});
		}else{
			$('#swipe li').eq(0).show();
			initPage(isInitSwipe);
		}
	}

	function initPage(isInitSwipe){

		$('#musicWrap').removeClass('f-hide');

		// 显示剪头
		if(isInitSwipe){
			$('#arrow' + (config.swipeDir == 'vertical' ? 'V' : 'H' )).removeClass('f-hide').children().addClass('move');
		}

		$('[data-role="video"]').each(function(){
			new Media(this, function(){
				if(audioPlayer.isPlay) audioPlayer._play();
			} ,function(){
				audioPlayer._play();
			});
		});

        // old_ygj_info = {};old_ygj_info.footer_title ="%E4%BA%91%E7%AE%A1%E5%AE%B6";old_ygj_info.footer_url ="http%3A%2F%2Fwww.ygj.com.cn%2F";
		// footer
		
   }
	


$(function(){

    if(typeof audioPlayer == "undefined"){
    	audioPlayer = new Player($('#audioBtn'));
    }
   
	//var lotteryEnd = false;
	// current swipe object
	var	curObj = $('#swipe li').eq(config.swipeCur);
	// get img src
	var curSrc = getSrc(curObj.find('div'));

	curSrc = $('#swipe li').first().find('.li_bg').eq(0).data('bginfo');

	// 解决ios safari 不自动播放的问题
    $(document).one('touchstart', function(){
      if(config.enable_music){
      	if(config.coverUrl){
    	       audioPlayer._audio.play();
    	       audioPlayer._audio.pause();
      	}else{
      	  audioPlayer._audio.play();
      	}
      }
    });

    //声音播放
    document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    	    WeixinJSBridge.invoke('getNetworkType', {}, 
    	    function(e) {
    	         var network = e.err_msg.split(":")[1]; 

    	       if(!config.coverUrl){
		    if(config.enable_music){
		    	   audioPlayer._audio.play();
		    }
    
		    if(!config.enable_music){
		    	   audioPlayer.isPlay = false;
		    	   $(audioPlayer.el).removeClass('on');
		    }
		  }
    	    });
    });

	// 判断是否需要立刻 模糊初始化
	if(curObj.data('role') == 'blur'){
		imgLoad(curSrc, function(){
			curObj.stackBlur({img: this, radius: 10, callback: function(){
				curObj.data('role', '');
				initLottery();
			}});
		}, initLottery);
	}else{
		imgLoad(curSrc, initLottery, initLottery);
	}
	


});