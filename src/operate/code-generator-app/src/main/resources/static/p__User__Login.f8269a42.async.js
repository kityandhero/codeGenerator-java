(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[35],{HDVZ:function(e,a,t){e.exports={main:"antd-pro-pages-user-login-style-main",icon:"antd-pro-pages-user-login-style-icon",other:"antd-pro-pages-user-login-style-other",register:"antd-pro-pages-user-login-style-register"}},UkyR:function(e,a,t){e.exports={login:"antd-pro-pages-user-login-components-login-index-login",getCaptcha:"antd-pro-pages-user-login-components-login-index-getCaptcha",icon:"antd-pro-pages-user-login-components-login-index-icon",other:"antd-pro-pages-user-login-components-login-index-other",register:"antd-pro-pages-user-login-components-login-index-register",prefixIcon:"antd-pro-pages-user-login-components-login-index-prefixIcon",submit:"antd-pro-pages-user-login-components-login-index-submit"}},zzaQ:function(e,a,t){"use strict";t.r(a);t("0US0");var n=t("Kw4v"),r=t("tUtL"),c=t("Wj6B"),o=(t("ChRb"),t("HOAw")),i=t("ZZRV"),s=t.n(i),l=t("fKlJ"),u=t("92AI"),m=function(e,a){return i["createElement"](u["a"],Object.assign({},e,{ref:a,icon:l["a"]}))};m.displayName="AlipayCircleOutlined";var p=i["forwardRef"](m),d=t("5qXv"),f=function(e,a){return i["createElement"](u["a"],Object.assign({},e,{ref:a,icon:d["a"]}))};f.displayName="TaobaoCircleOutlined";var g=i["forwardRef"](f),b=t("V0wr"),h=function(e,a){return i["createElement"](u["a"],Object.assign({},e,{ref:a,icon:b["a"]}))};h.displayName="WeiboCircleOutlined";var v=i["forwardRef"](h),E=t("FR5j"),O=t("9kvl"),j=(t("KJ1D"),t("mKGO")),w=(t("eoH/"),t("3lnK")),y=t("C7uU"),C=t("RYWf"),N=t("iczh"),x=t.n(N),T=Object(i["createContext"])({}),U=T,k=(t("zFcB"),t("+kI8")),R=(t("frik"),t("fGNf")),S=(t("NDU/"),t("TSE5")),I=(t("EXU6"),t("wYVW")),P=t("85q2"),q=t("RrRN"),V=t.n(q),z=(t("IOlT"),t("w1wR")),D=t("V/D1"),A=t("Mqci"),B=t("B1rl"),K=t("t3Un");function L(e){return M.apply(this,arguments)}function M(){return M=Object(D["a"])(V.a.mark((function e(a){return V.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",Object(K["a"])("/api/login/captcha?mobile=".concat(a)));case 1:case"end":return e.stop()}}),e)}))),M.apply(this,arguments)}var F=t("xbqU"),W=function(e,a){return i["createElement"](u["a"],Object.assign({},e,{ref:a,icon:F["a"]}))};W.displayName="UserOutlined";var G=i["forwardRef"](W),H=t("8OGv"),J=function(e,a){return i["createElement"](u["a"],Object.assign({},e,{ref:a,icon:H["a"]}))};J.displayName="LockTwoTone";var Z=i["forwardRef"](J),X=t("4uVU"),Y=function(e,a){return i["createElement"](u["a"],Object.assign({},e,{ref:a,icon:X["a"]}))};Y.displayName="MobileTwoTone";var $=i["forwardRef"](Y),Q=t("cBNl"),_=function(e,a){return i["createElement"](u["a"],Object.assign({},e,{ref:a,icon:Q["a"]}))};_.displayName="MailTwoTone";var ee=i["forwardRef"](_),ae=t("UkyR"),te=t.n(ae),ne={UserName:{props:{size:"large",id:"userName",prefix:s.a.createElement(G,{style:{color:"#1890ff"},className:te.a.prefixIcon}),placeholder:"admin"},rules:[{required:!0,message:"Please enter username!"}]},Password:{props:{size:"large",prefix:s.a.createElement(Z,{className:te.a.prefixIcon}),type:"password",id:"password",placeholder:"888888"},rules:[{required:!0,message:"Please enter password!"}]},Mobile:{props:{size:"large",prefix:s.a.createElement($,{className:te.a.prefixIcon}),placeholder:"mobile number"},rules:[{required:!0,message:"Please enter mobile number!"},{pattern:/^1\d{10}$/,message:"Wrong mobile number format!"}]},Captcha:{props:{size:"large",prefix:s.a.createElement(ee,{className:te.a.prefixIcon}),placeholder:"captcha"},rules:[{required:!0,message:"Please enter Captcha!"}]}},re=j["a"].Item,ce=function(e){var a=e.onChange,t=e.defaultValue,n=e.customProps,r=void 0===n?{}:n,c=e.rules,o={rules:c||r.rules};return a&&(o.onChange=a),t&&(o.initialValue=t),o},oe=function(e){var a=Object(i["useState"])(e.countDown||0),t=Object(c["a"])(a,2),n=t[0],r=t[1],o=Object(i["useState"])(!1),l=Object(c["a"])(o,2),u=l[0],m=l[1],p=(e.onChange,e.customProps),d=(e.defaultValue,e.rules,e.name),f=(e.getCaptchaButtonText,e.getCaptchaSecondText,e.updateActive,e.type),g=(e.tabUtil,Object(A["a"])(e,["onChange","customProps","defaultValue","rules","name","getCaptchaButtonText","getCaptchaSecondText","updateActive","type","tabUtil"])),b=Object(i["useCallback"])(function(){var e=Object(D["a"])(V.a.mark((function e(a){var t;return V.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,L(a);case 2:if(t=e.sent,!1!==t){e.next=5;break}return e.abrupt("return");case 5:z["a"].success("\u83b7\u53d6\u9a8c\u8bc1\u7801\u6210\u529f\uff01\u9a8c\u8bc1\u7801\u4e3a\uff1a1234"),m(!0);case 7:case"end":return e.stop()}}),e)})));return function(a){return e.apply(this,arguments)}}(),[]);if(Object(i["useEffect"])((function(){var a=0,t=e.countDown;return u&&(a=window.setInterval((function(){r((function(e){return e<=1?(m(!1),clearInterval(a),t||60):e-1}))}),1e3)),function(){return clearInterval(a)}}),[u]),!d)return null;var h=ce(e),v=g||{};if("Captcha"===f){var E=Object(B["a"])(v,["onGetCaptcha","countDown"]);return s.a.createElement(re,{shouldUpdate:!0},(function(e){var a=e.getFieldValue;return s.a.createElement(k["a"],{gutter:8},s.a.createElement(S["a"],{span:16},s.a.createElement(re,Object(P["a"])({name:d},h),s.a.createElement(I["a"],Object(P["a"])({},p,E)))),s.a.createElement(S["a"],{span:8},s.a.createElement(R["a"],{disabled:u,className:te.a.getCaptcha,size:"large",onClick:function(){var e=a("mobile");b(e)}},u?"".concat(n," \u79d2"):"\u83b7\u53d6\u9a8c\u8bc1\u7801")))}))}return s.a.createElement(re,Object(P["a"])({name:d},h),s.a.createElement(I["a"],Object(P["a"])({},p,v)))},ie={};Object.keys(ne).forEach((function(e){var a=ne[e];ie[e]=function(t){return s.a.createElement(U.Consumer,null,(function(n){return s.a.createElement(oe,Object(P["a"])({customProps:a.props,rules:a.rules},t,{type:e},n,{updateActive:n.updateActive}))}))}}));var se=ie,le=j["a"].Item,ue=function(e){var a=e.className,t=Object(A["a"])(e,["className"]),n=x()(te.a.submit,a);return s.a.createElement(le,null,s.a.createElement(R["a"],Object(P["a"])({size:"large",className:n,type:"primary",htmlType:"submit"},t)))},me=ue,pe=w["a"].TabPane,de=function(){var e=0;return function(){var a=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return e+=1,"".concat(a).concat(e)}}(),fe=function(e){Object(i["useEffect"])((function(){var a=de("login-tab-"),t=e.tabUtil;t&&t.addTab(a)}),[]);var a=e.children;return s.a.createElement(pe,e,e.active&&a)},ge=function(e){return s.a.createElement(U.Consumer,null,(function(a){return s.a.createElement(fe,Object(P["a"])({tabUtil:a.tabUtil},e))}))};ge.typeName="LoginTab";var be=ge,he=function(e){var a=e.className,t=Object(i["useState"])([]),n=Object(c["a"])(t,2),r=n[0],o=n[1],l=Object(i["useState"])(),u=Object(c["a"])(l,2),m=u[0],p=u[1],d=Object(C["a"])("",{value:e.activeKey,onChange:e.onTabChange}),f=Object(c["a"])(d,2),g=f[0],b=f[1],h=[],v=[];return s.a.Children.forEach(e.children,(function(e){e&&("LoginTab"===e.type.typeName?h.push(e):v.push(e))})),s.a.createElement(U.Provider,{value:{tabUtil:{addTab:function(e){o([].concat(Object(y["a"])(r),[e]))},removeTab:function(e){o(r.filter((function(a){return a!==e})))}},updateActive:function(e){m[g]?m[g].push(e):m[g]=[e],p(m)}}},s.a.createElement("div",{className:x()(a,te.a.login)},s.a.createElement(j["a"],{form:e.from,onFinish:function(a){e.onSubmit&&e.onSubmit(a)}},r.length?s.a.createElement(s.a.Fragment,null,s.a.createElement(w["a"],{animated:!1,className:te.a.tabs,activeKey:g,onChange:function(e){b(e)}},h),v):e.children)))};he.Tab=be,he.Submit=me,he.UserName=se.UserName,he.Password=se.Password,he.Mobile=se.Mobile,he.Captcha=se.Captcha;var ve=he,Ee=t("HDVZ"),Oe=t.n(Ee),je=ve.Tab,we=ve.UserName,ye=ve.Password,Ce=ve.Mobile,Ne=ve.Captcha,xe=ve.Submit,Te=function(e){var a=e.content;return s.a.createElement(o["a"],{style:{marginBottom:24},message:a,type:"error",showIcon:!0})},Ue=function(e){var a=e.userLogin,t=void 0===a?{}:a,o=e.submitting,l=t.status,u=t.type,m=Object(i["useState"])(!0),d=Object(c["a"])(m,2),f=d[0],b=d[1],h=Object(i["useState"])("account"),O=Object(c["a"])(h,2),j=O[0],w=O[1],y=function(a){var t=e.dispatch;t({type:"login/login",payload:Object(r["a"])({},a,{type:j})})};return s.a.createElement("div",{className:Oe.a.main},s.a.createElement(ve,{activeKey:j,onTabChange:w,onSubmit:y},s.a.createElement(je,{key:"account",tab:"\u8d26\u6237\u5bc6\u7801\u767b\u5f55"},"error"===l&&"account"===u&&!o&&s.a.createElement(Te,{content:"\u8d26\u6237\u6216\u5bc6\u7801\u9519\u8bef\uff08admin/ant.design\uff09"}),s.a.createElement(we,{name:"userName",placeholder:"\u7528\u6237\u540d: admin or user",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u7528\u6237\u540d!"}]}),s.a.createElement(ye,{name:"password",placeholder:"\u5bc6\u7801: ant.design",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u5bc6\u7801\uff01"}]})),s.a.createElement(je,{key:"mobile",tab:"\u624b\u673a\u53f7\u767b\u5f55"},"error"===l&&"mobile"===u&&!o&&s.a.createElement(Te,{content:"\u9a8c\u8bc1\u7801\u9519\u8bef"}),s.a.createElement(Ce,{name:"mobile",placeholder:"\u624b\u673a\u53f7",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u624b\u673a\u53f7\uff01"},{pattern:/^1\d{10}$/,message:"\u624b\u673a\u53f7\u683c\u5f0f\u9519\u8bef\uff01"}]}),s.a.createElement(Ne,{name:"captcha",placeholder:"\u9a8c\u8bc1\u7801",countDown:120,getCaptchaButtonText:"",getCaptchaSecondText:"\u79d2",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801\uff01"}]})),s.a.createElement("div",null,s.a.createElement(n["a"],{checked:f,onChange:function(e){return b(e.target.checked)}},"\u81ea\u52a8\u767b\u5f55"),s.a.createElement("a",{style:{float:"right"}},"\u5fd8\u8bb0\u5bc6\u7801")),s.a.createElement(xe,{loading:o},"\u767b\u5f55"),s.a.createElement("div",{className:Oe.a.other},"\u5176\u4ed6\u767b\u5f55\u65b9\u5f0f",s.a.createElement(p,{className:Oe.a.icon}),s.a.createElement(g,{className:Oe.a.icon}),s.a.createElement(v,{className:Oe.a.icon}),s.a.createElement(E["Link"],{className:Oe.a.register,to:"/user/register"},"\u6ce8\u518c\u8d26\u6237"))))};a["default"]=Object(O["a"])((function(e){var a=e.login,t=e.loading;return{userLogin:a,submitting:t.effects["login/login"]}}))(Ue)}}]);