(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[9],{"6uVI":function(e,a,t){e.exports={cardTitle:"antd-pro-pages-account-edit-basic-info-index-cardTitle",card:"antd-pro-pages-account-edit-basic-info-index-card"}},ePcR:function(e,a,t){"use strict";t.r(a);var n,r,c,i=t("k1fw"),l=(t("IzEo"),t("bx4M")),m=(t("T2oS"),t("W9HT")),d=(t("14J3"),t("BMrR")),o=(t("jCWc"),t("kPKH")),u=(t("1YHl"),t("VNzZ")),s=(t("/zsF"),t("PArb")),p=(t("/xke"),t("TeRw")),f=t("fWQN"),b=t("mtLc"),g=t("yKVA"),E=t("879j"),D=t("q1tI"),v=t.n(D),T=t("9kvl"),h=t("Fvcw"),j=t("uJMD"),I=t("7pZ8"),x=t.n(I),F=t("i0ey"),N=t("TAvc"),O=t("3Pbg"),P=t("EXmu"),w=t("6uVI"),A=t.n(w),Y=(n=Object(T["a"])((function(e){var a=e.account,t=e.global,n=e.loading;return{account:a,global:t,loading:n.models.account}})),n((c=function(e){Object(g["a"])(t,e);var a=Object(E["a"])(t);function t(e){var n;return Object(f["a"])(this,t),n=a.call(this,e),n.componentAuthority=x.a.account.get,n.goToUpdateWhenProcessed=!0,n.getApiData=function(e){var a=e.account.data;return a},n.buildInitialValues=function(e,a,t,n){var r={};return null!=e&&(r[P["fieldData"].userName.name]=e.userName||"",r[P["fieldData"].name.name]=e.name||"",r[P["fieldData"].description.name]=e.description||"",r[F["b"].createTime.name]=Object(j["l"])(e.createTime,"YYYY-MM-DD HH:mm")||"",r[F["b"].updateTime.name]=Object(j["l"])(e.updateTime,"YYYY-MM-DD HH:mm")||""),r},n.checkNeedUpdate=function(e,a,t){return Object(O["a"])(n.state,e,a,t)},n.supplementLoadRequestParams=function(e){var a=e,t=n.state.accountId;return a.accountId=t,a},n.supplementSubmitRequestParams=function(e){var a=e,t=n.state.accountId;return a.accountId=t,a},n.afterSubmitSuccess=function(e){requestAnimationFrame((function(){p["a"].success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u4fdd\u5b58\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u540e\u7eed\u64cd\u4f5c\u3002"})}))},n.formContent=function(){var e=n.state,a=e.processing,t=e.dataLoading;return v.a.createElement(v.a.Fragment,null,v.a.createElement(l["a"],{title:n.renderBasicInfoTitle(),className:A.a.card,bordered:!1,extra:v.a.createElement(u["a"],{offsetTop:20},v.a.createElement("div",null,n.renderRefreshButton(),v.a.createElement(s["a"],{type:"vertical"}),n.renderSaveButton()))},v.a.createElement(m["a"],{delay:500,spinning:t||a},v.a.createElement(d["a"],{gutter:24},v.a.createElement(o["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInput(P["fieldData"].userName.label,P["fieldData"].userName.name,!0,P["fieldData"].userName.helper,v.a.createElement(h["a"],null),null,!1)),v.a.createElement(o["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInput(P["fieldData"].name.label,P["fieldData"].name.name,!0,P["fieldData"].name.helper))))),v.a.createElement(l["a"],{title:"\u63cf\u8ff0\u4fe1\u606f",className:A.a.card,bordered:!1},v.a.createElement(m["a"],{delay:500,spinning:t||a},v.a.createElement(d["a"],{gutter:24},v.a.createElement(o["a"],{lg:24,md:24,sm:24,xs:24},n.renderFormTextArea(P["fieldData"].description.label,P["fieldData"].description.name,!1,P["fieldData"].description.helper))))),v.a.createElement(l["a"],{title:"\u5176\u4ed6\u4fe1\u606f",className:A.a.card,bordered:!1},v.a.createElement(m["a"],{delay:500,spinning:t||a},v.a.createElement(d["a"],{gutter:24},v.a.createElement(o["a"],{lg:6,md:12,sm:24,xs:24},n.renderFromCreateTimeField()),v.a.createElement(o["a"],{lg:6,md:12,sm:24,xs:24},n.renderFromUpdateTimeField())))))},n.state=Object(i["a"])(Object(i["a"])({},n.state),{loadApiPath:"account/get",submitApiPath:"account/updateBasicInfo"}),n}return Object(b["a"])(t,null,[{key:"getDerivedStateFromProps",value:function(e,a){return Object(j["n"])(e,a,{id:""},O["b"])}}]),t}(N["a"]),r=c))||r);a["default"]=Y}}]);