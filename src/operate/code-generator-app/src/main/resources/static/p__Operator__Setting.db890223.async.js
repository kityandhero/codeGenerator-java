(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[31],{NRFR:function(e,t,n){"use strict";n.r(t);var a,i,r,o=n("QyQr"),s=n("G2pS"),c=n("pUne"),l=n("euny"),u=(n("ASDf"),n("g9Mj")),m=n("ZZRV"),p=n.n(m),d=n("9kvl"),f=n("t+9W"),h=n("t6Xd"),v=n("rHlJ"),g=n.n(v),y=u["a"].Item,b=(a=Object(d["a"])((function(e){var t=e.account;return{account:t}})),a((r=function(e){Object(c["a"])(n,e);var t=Object(l["a"])(n);function n(e){var a;Object(o["a"])(this,n),a=t.call(this,e),a.doDidMountTask=function(){window.addEventListener("resize",a.resize),a.resize()},a.beforeUnmount=function(){window.removeEventListener("resize",a.resize)},a.getMenu=function(){var e=a.state.menuMap;return Object.keys(e).map((function(t){return p.a.createElement(y,{key:t},e[t])}))},a.getRightTitle=function(){var e=a.state,t=e.selectKey,n=e.menuMap;return n[t]},a.selectKey=function(e){var t=e.key;d["d"].push("/operator/setting/".concat(t)),a.setState({selectKey:t})},a.resize=function(){a.main&&requestAnimationFrame((function(){if(a.main){var e="inline",t=a.main.offsetWidth;a.main.offsetWidth<641&&t>400&&(e="horizontal"),window.innerWidth<768&&t>400&&(e="horizontal"),a.setState({mode:e})}}))};var i=e.match,r=e.location,s={base:"\u57fa\u672c\u8bbe\u7f6e",password:"\u66f4\u6539\u5bc6\u7801"},c=r.pathname.replace("".concat(i.path,"/"),"");return a.state={mode:"inline",menuMap:s,selectKey:s[c]?c:"base"},a}return Object(s["a"])(n,[{key:"render",value:function(){var e=this,t=this.props.children,n=this.state,a=n.mode,i=n.selectKey;return p.a.createElement(f["b"],null,p.a.createElement("div",{className:g.a.main,ref:function(t){e.main=t}},p.a.createElement("div",{className:g.a.leftMenu},p.a.createElement(u["a"],{mode:a,selectedKeys:[i],onClick:this.selectKey},this.getMenu())),p.a.createElement("div",{className:g.a.right},p.a.createElement("div",{className:g.a.title},this.getRightTitle()),t)))}}],[{key:"getDerivedStateFromProps",value:function(e,t){var n=e.match,a=e.location,i=a.pathname.replace("".concat(n.path,"/"),"");return i=t.menuMap[i]?i:"base",i!==t.selectKey?{selectKey:i}:null}}]),n}(h["a"]),i=r))||i);t["default"]=b},rHlJ:function(e,t,n){e.exports={main:"antd-pro-pages-operator-setting-index-main",leftMenu:"antd-pro-pages-operator-setting-index-leftMenu",right:"antd-pro-pages-operator-setting-index-right",title:"antd-pro-pages-operator-setting-index-title"}}}]);