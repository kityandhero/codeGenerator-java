(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[9],{"6uVI":function(e,a,t){e.exports={cardTitle:"antd-pro-pages-account-edit-basic-info-index-cardTitle",card:"antd-pro-pages-account-edit-basic-info-index-card"}},ePcR:function(e,a,t){"use strict";t.r(a);var n,r,c,i=t("xFFA"),m=(t("W868"),t("GSnu")),l=(t("37Jm"),t("9Jt9")),o=(t("TPHQ"),t("kA8J")),u=(t("YN1I"),t("KBK0")),d=(t("eYL8"),t("07et")),s=(t("WETC"),t("p3eG")),p=(t("xgFS"),t("m1fh")),f=t("QyQr"),b=t("G2pS"),g=t("pUne"),E=t("euny"),D=t("ZZRV"),v=t.n(D),F=t("9kvl"),I=t("a5c2"),h=t("uJMD"),T=t("7pZ8"),x=t.n(T),j=t("i0ey"),N=t("U1dv"),O=t("3Pbg"),Y=t("EXmu"),P=t("6uVI"),A=t.n(P),S=(n=Object(F["a"])((function(e){var a=e.account,t=e.global,n=e.loading;return{account:a,global:t,loading:n.models.account}})),n((c=function(e){Object(g["a"])(t,e);var a=Object(E["a"])(t);function t(e){var n;return Object(f["a"])(this,t),n=a.call(this,e),n.componentAuthority=x.a.account.get,n.goToUpdateWhenProcessed=!0,n.getApiData=function(e){var a=e.account.data;return a},n.buildInitialValues=function(e){var a={};return null!=e&&(a[Y["fieldData"].userName.name]=e.userName||"",a[Y["fieldData"].name.name]=e.name||"",a[Y["fieldData"].description.name]=e.description||"",a[j["a"].createTime.name]=Object(h["q"])(e.createTime,"YYYY-MM-DD HH:mm")||"",a[j["a"].updateTime.name]=Object(h["q"])(e.updateTime,"YYYY-MM-DD HH:mm")||""),a},n.checkNeedUpdate=function(e,a,t){return Object(O["a"])(n.state,e,a,t)},n.supplementLoadRequestParams=function(e){var a=e,t=n.state.accountId;return a.accountId=t,a},n.supplementSubmitRequestParams=function(e){var a=e,t=n.state.accountId;return a.accountId=t,a},n.afterSubmitSuccess=function(e){requestAnimationFrame((function(){p["a"].success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u4fdd\u5b58\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u540e\u7eed\u64cd\u4f5c\u3002"})}))},n.formContent=function(){var e=n.state,a=e.processing,t=e.dataLoading;return v.a.createElement(v.a.Fragment,null,v.a.createElement(m["a"],{title:n.renderBasicInfoTitle(),className:A.a.card,bordered:!1,extra:v.a.createElement(d["a"],{offsetTop:20},v.a.createElement("div",null,n.renderRefreshButton(),v.a.createElement(s["a"],{type:"vertical"}),n.renderSaveButton()))},v.a.createElement(l["a"],{spinning:t||a},v.a.createElement(o["a"],{gutter:24},v.a.createElement(u["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputFormItem(Y["fieldData"].userName.label,Y["fieldData"].userName.name,!0,Y["fieldData"].userName.helper,v.a.createElement(I["a"],null),null,!1)),v.a.createElement(u["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputFormItem(Y["fieldData"].name.label,Y["fieldData"].name.name,!0,Y["fieldData"].name.helper))))),v.a.createElement(m["a"],{title:"\u63cf\u8ff0\u4fe1\u606f",className:A.a.card,bordered:!1},v.a.createElement(l["a"],{spinning:t||a},v.a.createElement(o["a"],{gutter:24},v.a.createElement(u["a"],{lg:24,md:24,sm:24,xs:24},n.renderFormTextAreaFormItem(Y["fieldData"].description.label,Y["fieldData"].description.name,!1,Y["fieldData"].description.helper))))),v.a.createElement(m["a"],{title:"\u5176\u4ed6\u4fe1\u606f",className:A.a.card,bordered:!1},v.a.createElement(l["a"],{spinning:t||a},v.a.createElement(o["a"],{gutter:24},v.a.createElement(u["a"],{lg:6,md:12,sm:24,xs:24},n.renderFromCreateTimeField()),v.a.createElement(u["a"],{lg:6,md:12,sm:24,xs:24},n.renderFromUpdateTimeField())))))},n.state=Object(i["a"])({},n.state,{},{loadApiPath:"account/get",submitApiPath:"account/updateBasicInfo"}),n}return Object(b["a"])(t,null,[{key:"getDerivedStateFromProps",value:function(e,a){return Object(h["r"])(e,a,{id:""},O["b"])}}]),t}(N["a"]),r=c))||r);a["default"]=S}}]);