(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[33],{HDVZ:function(e,a,t){e.exports={main:"antd-pro-pages-user-login-style-main",icon:"antd-pro-pages-user-login-style-icon",other:"antd-pro-pages-user-login-style-other",register:"antd-pro-pages-user-login-style-register"}},UkyR:function(e,a,t){e.exports={login:"antd-pro-pages-user-login-components-login-index-login",getCaptcha:"antd-pro-pages-user-login-components-login-index-getCaptcha",icon:"antd-pro-pages-user-login-components-login-index-icon",other:"antd-pro-pages-user-login-components-login-index-other",register:"antd-pro-pages-user-login-components-login-index-register",prefixIcon:"antd-pro-pages-user-login-components-login-index-prefixIcon",submit:"antd-pro-pages-user-login-components-login-index-submit"}},zzaQ:function(e,a,t){"use strict";t.r(a);t("sRBo");var n=t("kaz8"),r=t("k1fw"),c=t("tJVT"),o=(t("fOrg"),t("+KLJ")),i=t("q1tI"),s=t.n(i),l=t("YCi6"),u=t("6VBw"),m=function(e,a){return s.a.createElement(u["a"],Object.assign({},e,{ref:a,icon:l["a"]}))};m.displayName="AlipayCircleOutlined";var p=s.a.forwardRef(m),d=t("3ade"),g=function(e,a){return s.a.createElement(u["a"],Object.assign({},e,{ref:a,icon:d["a"]}))};g.displayName="TaobaoCircleOutlined";var f=s.a.forwardRef(g),b=t("VsbB"),h=function(e,a){return s.a.createElement(u["a"],Object.assign({},e,{ref:a,icon:b["a"]}))};h.displayName="WeiboCircleOutlined";var v=s.a.forwardRef(h),E=t("55Ip"),O=t("/MKj"),j=(t("y8nQ"),t("Vl3Y")),y=(t("Znn+"),t("ZTPi")),w=t("oBTY"),C=t("yUgw"),N=t("TSYQ"),x=t.n(N),T=Object(i["createContext"])({}),k=T,P=(t("14J3"),t("BMrR")),S=(t("+L6B"),t("2/Rp")),U=(t("jCWc"),t("kPKH")),I=(t("5NDa"),t("5rEg")),R=t("0Owb"),B=t("WmNS"),V=t.n(B),q=(t("miYZ"),t("tsqr")),z=t("9og8"),L=t("PpiC"),M=t("BGR+"),D=t("t3Un");function Y(e){return A.apply(this,arguments)}function A(){return A=Object(z["a"])(V.a.mark((function e(a){return V.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",Object(D["a"])("/api/login/captcha?mobile=".concat(a)));case 1:case"end":return e.stop()}}),e)}))),A.apply(this,arguments)}var J=t("cJ7L"),K=t("XXwa"),Z=function(e,a){return s.a.createElement(u["a"],Object.assign({},e,{ref:a,icon:K["a"]}))};Z.displayName="LockTwoTone";var W=s.a.forwardRef(Z),F=t("RVY5"),H=function(e,a){return s.a.createElement(u["a"],Object.assign({},e,{ref:a,icon:F["a"]}))};H.displayName="MobileTwoTone";var Q=s.a.forwardRef(H),G=t("thYB"),X=function(e,a){return s.a.createElement(u["a"],Object.assign({},e,{ref:a,icon:G["a"]}))};X.displayName="MailTwoTone";var $=s.a.forwardRef(X),_=t("UkyR"),ee=t.n(_),ae={UserName:{props:{size:"large",id:"userName",prefix:s.a.createElement(J["a"],{style:{color:"#1890ff"},className:ee.a.prefixIcon}),placeholder:"admin"},rules:[{required:!0,message:"Please enter username!"}]},Password:{props:{size:"large",prefix:s.a.createElement(W,{className:ee.a.prefixIcon}),type:"password",id:"password",placeholder:"888888"},rules:[{required:!0,message:"Please enter password!"}]},Mobile:{props:{size:"large",prefix:s.a.createElement(Q,{className:ee.a.prefixIcon}),placeholder:"mobile number"},rules:[{required:!0,message:"Please enter mobile number!"},{pattern:/^1\d{10}$/,message:"Wrong mobile number format!"}]},Captcha:{props:{size:"large",prefix:s.a.createElement($,{className:ee.a.prefixIcon}),placeholder:"captcha"},rules:[{required:!0,message:"Please enter Captcha!"}]}},te=j["a"].Item,ne=function(e){var a=e.onChange,t=e.defaultValue,n=e.customProps,r=void 0===n?{}:n,c=e.rules,o={rules:c||r.rules};return a&&(o.onChange=a),t&&(o.initialValue=t),o},re=function(e){var a=Object(i["useState"])(e.countDown||0),t=Object(c["a"])(a,2),n=t[0],r=t[1],o=Object(i["useState"])(!1),l=Object(c["a"])(o,2),u=l[0],m=l[1],p=(e.onChange,e.customProps),d=(e.defaultValue,e.rules,e.name),g=(e.getCaptchaButtonText,e.getCaptchaSecondText,e.updateActive,e.type),f=(e.tabUtil,Object(L["a"])(e,["onChange","customProps","defaultValue","rules","name","getCaptchaButtonText","getCaptchaSecondText","updateActive","type","tabUtil"])),b=Object(i["useCallback"])(function(){var e=Object(z["a"])(V.a.mark((function e(a){var t;return V.a.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,Y(a);case 2:if(t=e.sent,!1!==t){e.next=5;break}return e.abrupt("return");case 5:q["a"].success("\u83b7\u53d6\u9a8c\u8bc1\u7801\u6210\u529f\uff01\u9a8c\u8bc1\u7801\u4e3a\uff1a1234"),m(!0);case 7:case"end":return e.stop()}}),e)})));return function(a){return e.apply(this,arguments)}}(),[]);if(Object(i["useEffect"])((function(){var a=0,t=e.countDown;return u&&(a=window.setInterval((function(){r((function(e){return e<=1?(m(!1),clearInterval(a),t||60):e-1}))}),1e3)),function(){return clearInterval(a)}}),[u]),!d)return null;var h=ne(e),v=f||{};if("Captcha"===g){var E=Object(M["a"])(v,["onGetCaptcha","countDown"]);return s.a.createElement(te,{shouldUpdate:!0},(function(e){var a=e.getFieldValue;return s.a.createElement(P["a"],{gutter:8},s.a.createElement(U["a"],{span:16},s.a.createElement(te,Object(R["a"])({name:d},h),s.a.createElement(I["a"],Object(R["a"])({},p,E)))),s.a.createElement(U["a"],{span:8},s.a.createElement(S["a"],{disabled:u,className:ee.a.getCaptcha,size:"large",onClick:function(){var e=a("mobile");b(e)}},u?"".concat(n," \u79d2"):"\u83b7\u53d6\u9a8c\u8bc1\u7801")))}))}return s.a.createElement(te,Object(R["a"])({name:d},h),s.a.createElement(I["a"],Object(R["a"])({},p,v)))},ce={};Object.keys(ae).forEach((function(e){var a=ae[e];ce[e]=function(t){return s.a.createElement(k.Consumer,null,(function(n){return s.a.createElement(re,Object(R["a"])({customProps:a.props,rules:a.rules},t,{type:e},n,{updateActive:n.updateActive}))}))}}));var oe=ce,ie=j["a"].Item,se=function(e){var a=e.className,t=Object(L["a"])(e,["className"]),n=x()(ee.a.submit,a);return s.a.createElement(ie,null,s.a.createElement(S["a"],Object(R["a"])({size:"large",className:n,type:"primary",htmlType:"submit"},t)))},le=se,ue=y["a"].TabPane,me=function(){var e=0;return function(){var a=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return e+=1,"".concat(a).concat(e)}}(),pe=function(e){Object(i["useEffect"])((function(){var a=me("login-tab-"),t=e.tabUtil;t&&t.addTab(a)}),[]);var a=e.children;return s.a.createElement(ue,e,e.active&&a)},de=function(e){return s.a.createElement(k.Consumer,null,(function(a){return s.a.createElement(pe,Object(R["a"])({tabUtil:a.tabUtil},e))}))};de.typeName="LoginTab";var ge=de,fe=function(e){var a=e.className,t=Object(i["useState"])([]),n=Object(c["a"])(t,2),r=n[0],o=n[1],l=Object(i["useState"])(),u=Object(c["a"])(l,2),m=u[0],p=u[1],d=Object(C["a"])("",{value:e.activeKey,onChange:e.onTabChange}),g=Object(c["a"])(d,2),f=g[0],b=g[1],h=[],v=[];return s.a.Children.forEach(e.children,(function(e){e&&("LoginTab"===e.type.typeName?h.push(e):v.push(e))})),s.a.createElement(k.Provider,{value:{tabUtil:{addTab:function(e){o([].concat(Object(w["a"])(r),[e]))},removeTab:function(e){o(r.filter((function(a){return a!==e})))}},updateActive:function(e){m[f]?m[f].push(e):m[f]=[e],p(m)}}},s.a.createElement("div",{className:x()(a,ee.a.login)},s.a.createElement(j["a"],{form:e.from,onFinish:function(a){e.onSubmit&&e.onSubmit(a)}},r.length?s.a.createElement(s.a.Fragment,null,s.a.createElement(y["a"],{animated:!1,className:ee.a.tabs,activeKey:f,onChange:function(e){b(e)}},h),v):e.children)))};fe.Tab=ge,fe.Submit=le,fe.UserName=oe.UserName,fe.Password=oe.Password,fe.Mobile=oe.Mobile,fe.Captcha=oe.Captcha;var be=fe,he=t("HDVZ"),ve=t.n(he),Ee=be.Tab,Oe=be.UserName,je=be.Password,ye=be.Mobile,we=be.Captcha,Ce=be.Submit,Ne=function(e){var a=e.content;return s.a.createElement(o["a"],{style:{marginBottom:24},message:a,type:"error",showIcon:!0})},xe=function(e){var a=e.userLogin,t=void 0===a?{}:a,o=e.submitting,l=t.status,u=t.type,m=Object(i["useState"])(!0),d=Object(c["a"])(m,2),g=d[0],b=d[1],h=Object(i["useState"])("account"),O=Object(c["a"])(h,2),j=O[0],y=O[1],w=function(a){var t=e.dispatch;t({type:"login/login",payload:Object(r["a"])({},a,{type:j})})};return s.a.createElement("div",{className:ve.a.main},s.a.createElement(be,{activeKey:j,onTabChange:y,onSubmit:w},s.a.createElement(Ee,{key:"account",tab:"\u8d26\u6237\u5bc6\u7801\u767b\u5f55"},"error"===l&&"account"===u&&!o&&s.a.createElement(Ne,{content:"\u8d26\u6237\u6216\u5bc6\u7801\u9519\u8bef\uff08admin/ant.design\uff09"}),s.a.createElement(Oe,{name:"userName",placeholder:"\u7528\u6237\u540d: admin or user",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u7528\u6237\u540d!"}]}),s.a.createElement(je,{name:"password",placeholder:"\u5bc6\u7801: ant.design",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u5bc6\u7801\uff01"}]})),s.a.createElement(Ee,{key:"mobile",tab:"\u624b\u673a\u53f7\u767b\u5f55"},"error"===l&&"mobile"===u&&!o&&s.a.createElement(Ne,{content:"\u9a8c\u8bc1\u7801\u9519\u8bef"}),s.a.createElement(ye,{name:"mobile",placeholder:"\u624b\u673a\u53f7",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u624b\u673a\u53f7\uff01"},{pattern:/^1\d{10}$/,message:"\u624b\u673a\u53f7\u683c\u5f0f\u9519\u8bef\uff01"}]}),s.a.createElement(we,{name:"captcha",placeholder:"\u9a8c\u8bc1\u7801",countDown:120,getCaptchaButtonText:"",getCaptchaSecondText:"\u79d2",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801\uff01"}]})),s.a.createElement("div",null,s.a.createElement(n["a"],{checked:g,onChange:function(e){return b(e.target.checked)}},"\u81ea\u52a8\u767b\u5f55"),s.a.createElement("a",{style:{float:"right"}},"\u5fd8\u8bb0\u5bc6\u7801")),s.a.createElement(Ce,{loading:o},"\u767b\u5f55"),s.a.createElement("div",{className:ve.a.other},"\u5176\u4ed6\u767b\u5f55\u65b9\u5f0f",s.a.createElement(p,{className:ve.a.icon}),s.a.createElement(f,{className:ve.a.icon}),s.a.createElement(v,{className:ve.a.icon}),s.a.createElement(E["Link"],{className:ve.a.register,to:"/user/register"},"\u6ce8\u518c\u8d26\u6237"))))};a["default"]=Object(O["c"])((function(e){var a=e.login,t=e.loading;return{userLogin:a,submitting:t.effects["login/login"]}}))(xe)}}]);