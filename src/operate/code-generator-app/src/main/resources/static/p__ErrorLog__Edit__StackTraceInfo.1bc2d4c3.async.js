(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[23],{PoZt:function(e,t,a){"use strict";a.r(t);var n,r,o,c=a("xFFA"),l=(a("W868"),a("GSnu")),i=(a("37Jm"),a("9Jt9")),u=(a("TPHQ"),a("kA8J")),m=(a("YN1I"),a("KBK0")),s=(a("eYL8"),a("07et")),d=a("QyQr"),g=a("G2pS"),p=a("pUne"),f=a("euny"),b=a("ZZRV"),v=a.n(b),Y=a("9kvl"),k=a("cCYE"),y=a("uJMD"),T=a("i0ey"),j=a("7pZ8"),E=a.n(j),O=a("PW4v"),h=a("uyCD"),J=a("dYgQ"),D=a.n(J),L=(n=Object(Y["a"])((function(e){var t=e.errorLog,a=e.global,n=e.loading;return{errorLog:t,global:a,loading:n.models.errorLog}})),n((o=function(e){Object(p["a"])(a,e);var t=Object(f["a"])(a);function a(e){var n;return Object(d["a"])(this,a),n=t.call(this,e),n.componentAuthority=E.a.errorLog.get,n.buildInitialValues=function(e,t,a,n){var r={};return null!=e&&(r[T["a"].channelNote.name]=e.channelNote||"",r[T["a"].createTime.name]=Object(y["l"])(e.createTime,"YYYY-MM-DD HH:mm")||"",r[T["a"].updateTime.name]=Object(y["l"])(e.updateTime,"YYYY-MM-DD HH:mm")||""),r},n.getFormLayout=function(){return"horizontal"},n.renderBasicInfoTitleText=function(){return"\u5806\u6808\u4fe1\u606f"},n.formContent=function(){var e=n.state,t=e.dataLoading,a=e.processing,r=e.metaData,o=null==r?[]:r.stackTraceJson||[];return o=o.map((function(e,t){var a={};return a.key="stackTraceJsonItem_".concat(t),a.data=e,a})),v.a.createElement(v.a.Fragment,null,v.a.createElement(l["a"],{title:n.renderBasicInfoTitle(),className:D.a.card,bordered:!1,extra:v.a.createElement(s["a"],{offsetTop:20},v.a.createElement("div",null,n.renderRefreshButton()))},v.a.createElement(i["a"],{spinning:t||a},v.a.createElement(u["a"],{gutter:24},null==r?null:v.a.createElement(v.a.Fragment,null,o.map((function(e){return v.a.createElement(m["a"],{key:e.key,span:24},v.a.createElement(k["a"],{language:"javascript"},JSON.stringify(e.data||"",null,"    ")))})))))))},n.state=Object(c["a"])({},n.state,{},{loadApiPath:"errorLog/get"}),n}return Object(g["a"])(a,null,[{key:"getDerivedStateFromProps",value:function(e,t){return Object(y["m"])(e,t,{id:""},h["b"])}}]),a}(O["a"]),r=o))||r);t["default"]=L},dYgQ:function(e,t,a){e.exports={card:"antd-pro-pages-error-log-edit-stack-trace-info-index-card"}}}]);