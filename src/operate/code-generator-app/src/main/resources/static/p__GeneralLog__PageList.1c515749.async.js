(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[27],{khvX:function(e,t,a){"use strict";a.r(t);var n,r,l,c=a("tUtL"),i=(a("oJeT"),a("QP/E")),o=(a("2NwG"),a("OScy")),u=(a("zFcB"),a("+kI8")),m=(a("NDU/"),a("TSE5")),d=a("Xh3J"),g=a("B6Sf"),f=a("K89m"),s=a("elvB"),h=a("ZZRV"),p=a.n(h),b=a("9kvl"),E=a("3rCl"),v=a("vReF"),F=a("uJMD"),y=a("ydnR"),I=a("7pZ8"),O=a.n(I),D=a("i0ey"),w=a("zmXK"),L=a("lLw4"),k=a("paK7"),j=a("bDL1");function R(e){return function(){var t,a=Object(f["a"])(e);if(S()){var n=Object(f["a"])(this).constructor;t=Reflect.construct(a,arguments,n)}else t=a.apply(this,arguments);return Object(g["a"])(this,t)}}function S(){if("undefined"===typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"===typeof Proxy)return!0;try{return Date.prototype.toString.call(Reflect.construct(Date,[],(function(){}))),!0}catch(e){return!1}}var C=(n=Object(b["a"])((function(e){var t=e.generalLog,a=e.global,n=e.loading;return{generalLog:t,global:a,loading:n.models.generalLog}})),n((l=function(e){Object(s["a"])(a,e);var t=R(a);function a(e){var n;return Object(d["a"])(this,a),n=t.call(this,e),n.getApiData=function(e){var t=e.generalLog.data;return t},n.goToEdit=function(e){var t=e.generalLogId,a={pathname:"/generalLog/edit/load/".concat(t,"/key/basicInfo")};b["d"].push(a)},n.renderSimpleFormInitialValues=function(){var e={};return e[D["a"].channel.name]=y["unlimitedWithStringFlag"].flag,e},n.renderSimpleFormRow=function(){return p.a.createElement(p.a.Fragment,null,p.a.createElement(u["a"],{gutter:24},p.a.createElement(m["a"],{md:6,sm:24},n.renderSearchInputFormItem(j["fieldData"].message.label,j["fieldData"].message.name)),p.a.createElement(m["a"],{md:6,sm:24},n.renderSearchChannelFormItem(!0)),n.renderSimpleFormButton()))},n.getColumn=function(){return[{title:j["fieldData"].message.label,dataIndex:j["fieldData"].message.name,align:"left",render:function(e){return p.a.createElement(p.a.Fragment,null,p.a.createElement(L["a"],{tooltip:!0,lines:1},e))}},{title:j["fieldData"].generalLogId.label,dataIndex:j["fieldData"].generalLogId.name,width:120,align:"center",render:function(e){return p.a.createElement(p.a.Fragment,null,p.a.createElement(k["a"],{tooltip:!0,lines:1,removeChildren:!0,extraContent:p.a.createElement(p.a.Fragment,null,p.a.createElement("a",{onClick:function(){Object(F["h"])(e)}},Object(F["O"])(e,"***",2,6)))},e," [\u70b9\u51fb\u590d\u5236]"))}},{title:D["a"].channel.label,dataIndex:D["a"].channel.name,width:160,align:"center",render:function(e,t){return p.a.createElement(p.a.Fragment,null,p.a.createElement(L["a"],{tooltip:!0,lines:1},t.channelNote))}},{title:D["a"].createTime.label,dataIndex:D["a"].createTime.name,width:140,align:"center",sorter:!1,render:function(e){return p.a.createElement(p.a.Fragment,null,p.a.createElement(L["a"],{tooltip:!0,lines:1},""===(e||"")?"--":Object(F["q"])(Object(F["W"])(e),"YYYY-MM-DD HH:mm")))}},{title:D["a"].customOperate.label,dataIndex:D["a"].customOperate.name,width:120,fixed:"right",align:"center",render:function(e,t){return p.a.createElement(p.a.Fragment,null,p.a.createElement(i["a"].Button,{size:"small",onClick:function(){return n.goToEdit(t)},disabled:!n.checkAuthority(O.a.account.get),overlay:p.a.createElement(o["a"],{onClick:function(e){return n.handleMenuClick(e,t)}},p.a.createElement(o["a"].Item,{key:"analysis",disabled:!0},p.a.createElement(E["a"],null),"\u5206\u6790"))},p.a.createElement(v["a"],null),"\u67e5\u770b"))}}]},n.state=Object(c["a"])({},n.state,{},{pageName:"\u6a21\u5757\u5217\u8868",paramsKey:"53e093b4-70d0-4a37-8eee-e8bf2ff3f687",loadApiPath:"generalLog/pageList",dateRangeFieldName:"\u751f\u6210\u65f6\u6bb5"}),n}return a}(w["a"]),r=l))||r);t["default"]=C}}]);