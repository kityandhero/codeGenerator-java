(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[29],{cBTy:function(e,a,t){"use strict";t.r(a);t("IzEo");var n,l,c,r=t("bx4M"),o=(t("4eJr"),t("3LgI")),i=(t("Telt"),t("Tckk")),s=t("k1fw"),u=(t("+L6B"),t("2/Rp")),p=t("fWQN"),m=t("mtLc"),d=t("yKVA"),g=t("879j"),h=t("q1tI"),b=t.n(h),f=t("9kvl"),k=t("GYYy"),v=t("uJMD"),y=t("ZUt8"),E=t("Ul7h"),j="./index.less",L="/logo.png",O=(n=Object(f["a"])((function(e){var a=e.help,t=e.global,n=e.loading;return{help:a,global:t,loading:n.models.help}})),n((c=function(e){Object(d["a"])(t,e);var a=Object(g["a"])(t);function t(e){var n;return Object(p["a"])(this,t),n=a.call(this,e),n.getApiData=function(e){var a=e.help.data;return a},n.supplementLoadRequestParams=function(e){var a=e,t=n.props.match,l=t.params,c=l.id;return a.helpId=c,a},n.afterLoadSuccess=function(e,a,t,l){var c=e.helpCategoryId;n.setState({pageName:e.helpCategoryName,backPath:"/helpCenter/category/".concat(c,"/pageList")})},n.pageHeaderAction=function(){var e=n.state.backPath;return""===(e||"")?null:b.a.createElement(u["a"],{icon:b.a.createElement(k["a"],null),size:"small",onClick:function(e){n.backToList(e)}},"\u5217\u8868\u9875")},n.state=Object(s["a"])(Object(s["a"])({},n.state),{pageName:"\u5e2e\u52a9\u8be6\u60c5",loadApiPath:"help/get",backPath:"/helpCenter/category/no/pageList"}),n}return Object(m["a"])(t,[{key:"render",value:function(){var e=this.state,a=e.pageName,t=e.metaData,n=e.dataLoading,l=e.loadSuccess;return b.a.createElement(r["a"],{title:a,bordered:!1,className:j.containorBox,loading:n||!l,extra:this.pageHeaderAction()},b.a.createElement("h2",{style:{textAlign:"center"}},null==t?"":t.title),b.a.createElement("p",{style:{textAlign:"center"}},b.a.createElement(i["a"],{src:L,size:"small"})," \u53d1\u5e03\u4e0e"," ",null==t?"":Object(v["l"])(t.inTime,"YYYY-MM-DD HH:mm","")),b.a.createElement("div",null,b.a.createElement(E["a"],null,null==t?"":t.content)),b.a.createElement(o["a"],null))}}]),t}(y["a"]),l=c))||l);a["default"]=O}}]);