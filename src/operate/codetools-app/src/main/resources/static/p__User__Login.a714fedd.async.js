(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[9],{"7PPT":function(e,t,a){e.exports={login:"antd-pro-pages-user-login-components-login-index-login",getCaptcha:"antd-pro-pages-user-login-components-login-index-getCaptcha",icon:"antd-pro-pages-user-login-components-login-index-icon",other:"antd-pro-pages-user-login-components-login-index-other",register:"antd-pro-pages-user-login-components-login-index-register",prefixIcon:"antd-pro-pages-user-login-components-login-index-prefixIcon",submit:"antd-pro-pages-user-login-components-login-index-submit"}},CHL8:function(e,t,a){"use strict";var n=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("X1LF");var r=n(a("QfOC")),l=n(a("zAuD")),u=n(a("BG4o"));a("oV3r");var o=n(a("DJcp")),c=n(a("2w0b")),i=n(a("iczh")),d=n(a("7PPT")),s=o.default.Item,f=function(e){var t=e.className,a=(0,u.default)(e,["className"]),n=(0,i.default)(d.default.submit,t);return c.default.createElement(s,null,c.default.createElement(r.default,(0,l.default)({size:"large",className:n,type:"primary",htmlType:"submit"},a)))},p=f;t.default=p},K4Vm:function(e,t,a){"use strict";var n=a("fbTi"),r=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("oV3r");var l=r(a("DJcp"));a("N2W2");var u=r(a("5KpO")),o=r(a("rXjv")),c=r(a("cO38")),i=n(a("2w0b")),d=r(a("RYWf")),s=r(a("iczh")),f=r(a("YM0q")),p=r(a("uOkS")),m=r(a("CHL8")),g=r(a("wqp7")),v=r(a("7PPT")),h=function(e){var t=e.className,a=(0,i.useState)([]),n=(0,c.default)(a,2),r=n[0],p=n[1],m=(0,i.useState)(),g=(0,c.default)(m,2),h=g[0],b=g[1],E=(0,d.default)("",{value:e.activeKey,onChange:e.onTabChange}),y=(0,c.default)(E,2),C=y[0],w=y[1],T=[],x=[];return i.default.Children.forEach(e.children,function(e){e&&("LoginTab"===e.type.typeName?T.push(e):x.push(e))}),i.default.createElement(f.default.Provider,{value:{tabUtil:{addTab:function(e){p([].concat((0,o.default)(r),[e]))},removeTab:function(e){p(r.filter(function(t){return t!==e}))}},updateActive:function(e){h[C]?h[C].push(e):h[C]=[e],b(h)}}},i.default.createElement("div",{className:(0,s.default)(t,v.default.login)},i.default.createElement(l.default,{form:e.from,onFinish:function(t){e.onSubmit&&e.onSubmit(t)}},r.length?i.default.createElement(i.default.Fragment,null,i.default.createElement(u.default,{animated:!1,className:v.default.tabs,activeKey:C,onChange:function(e){w(e)}},T),x):e.children)))};h.Tab=g.default,h.Submit=m.default,h.UserName=p.default.UserName,h.Password=p.default.Password,h.Mobile=p.default.Mobile,h.Captcha=p.default.Captcha;var b=h;t.default=b},OalY:function(e,t,a){"use strict";var n=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var r=a("vGWC"),l=n(a("2w0b")),u=n(a("7PPT")),o={UserName:{props:{size:"large",id:"userName",prefix:l.default.createElement(r.UserOutlined,{style:{color:"#1890ff"},className:u.default.prefixIcon}),placeholder:"admin"},rules:[{required:!0,message:"Please enter username!"}]},Password:{props:{size:"large",prefix:l.default.createElement(r.LockTwoTone,{className:u.default.prefixIcon}),type:"password",id:"password",placeholder:"888888"},rules:[{required:!0,message:"Please enter password!"}]},Mobile:{props:{size:"large",prefix:l.default.createElement(r.MobileTwoTone,{className:u.default.prefixIcon}),placeholder:"mobile number"},rules:[{required:!0,message:"Please enter mobile number!"},{pattern:/^1\d{10}$/,message:"Wrong mobile number format!"}]},Captcha:{props:{size:"large",prefix:l.default.createElement(r.MailTwoTone,{className:u.default.prefixIcon}),placeholder:"captcha"},rules:[{required:!0,message:"Please enter Captcha!"}]}};t.default=o},TVJM:function(e,t,a){e.exports={main:"antd-pro-pages-user-login-style-main",icon:"antd-pro-pages-user-login-style-icon",other:"antd-pro-pages-user-login-style-other",register:"antd-pro-pages-user-login-style-register"}},YM0q:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=a("2w0b"),r=(0,n.createContext)({}),l=r;t.default=l},anxO:function(e,t,a){"use strict";var n=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.fakeAccountLogin=o,t.getFakeCaptcha=i;var r=n(a("Ico4")),l=n(a("UWy3")),u=n(a("t3Un"));function o(e){return c.apply(this,arguments)}function c(){return c=(0,l.default)(r.default.mark(function e(t){return r.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.default)("/api/login/account",{method:"POST",data:t}));case 1:case"end":return e.stop()}},e)})),c.apply(this,arguments)}function i(e){return d.apply(this,arguments)}function d(){return d=(0,l.default)(r.default.mark(function e(t){return r.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",(0,u.default)("/api/login/captcha?mobile=".concat(t)));case 1:case"end":return e.stop()}},e)})),d.apply(this,arguments)}},uOkS:function(e,t,a){"use strict";var n=a("fbTi"),r=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("yKvZ");var l=r(a("oSHl"));a("X1LF");var u=r(a("QfOC"));a("15e7");var o=r(a("D46T"));a("kUSG");var c=r(a("2hxE")),i=r(a("zAuD")),d=r(a("Ico4"));a("kx0C");var s=r(a("fGAK")),f=r(a("UWy3")),p=r(a("BG4o")),m=r(a("cO38"));a("oV3r");var g=r(a("DJcp")),v=n(a("2w0b")),h=r(a("B1rl")),b=a("anxO"),E=r(a("OalY")),y=r(a("YM0q")),C=r(a("7PPT")),w=g.default.Item,T=function(e){var t=e.onChange,a=e.defaultValue,n=e.customProps,r=void 0===n?{}:n,l=e.rules,u={rules:l||r.rules};return t&&(u.onChange=t),a&&(u.initialValue=a),u},x=function(e){var t=(0,v.useState)(e.countDown||0),a=(0,m.default)(t,2),n=a[0],r=a[1],g=(0,v.useState)(!1),E=(0,m.default)(g,2),y=E[0],x=E[1],P=(e.onChange,e.customProps),N=(e.defaultValue,e.rules,e.name),O=(e.getCaptchaButtonText,e.getCaptchaSecondText,e.updateActive,e.type),U=(e.tabUtil,(0,p.default)(e,["onChange","customProps","defaultValue","rules","name","getCaptchaButtonText","getCaptchaSecondText","updateActive","type","tabUtil"])),k=(0,v.useCallback)(function(){var e=(0,f.default)(d.default.mark(function e(t){var a;return d.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.next=2,(0,b.getFakeCaptcha)(t);case 2:if(a=e.sent,!1!==a){e.next=5;break}return e.abrupt("return");case 5:s.default.success("\u83b7\u53d6\u9a8c\u8bc1\u7801\u6210\u529f\uff01\u9a8c\u8bc1\u7801\u4e3a\uff1a1234"),x(!0);case 7:case"end":return e.stop()}},e)}));return function(t){return e.apply(this,arguments)}}(),[]);if((0,v.useEffect)(function(){var t=0,a=e.countDown;return y&&(t=window.setInterval(function(){r(function(e){return e<=1?(x(!1),clearInterval(t),a||60):e-1})},1e3)),function(){return clearInterval(t)}},[y]),!N)return null;var M=T(e),S=U||{};if("Captcha"===O){var _=(0,h.default)(S,["onGetCaptcha","countDown"]);return v.default.createElement(w,{shouldUpdate:!0},function(e){var t=e.getFieldValue;return v.default.createElement(l.default,{gutter:8},v.default.createElement(o.default,{span:16},v.default.createElement(w,(0,i.default)({name:N},M),v.default.createElement(c.default,(0,i.default)({},P,_)))),v.default.createElement(o.default,{span:8},v.default.createElement(u.default,{disabled:y,className:C.default.getCaptcha,size:"large",onClick:function(){var e=t("mobile");k(e)}},y?"".concat(n," \u79d2"):"\u83b7\u53d6\u9a8c\u8bc1\u7801")))})}return v.default.createElement(w,(0,i.default)({name:N},M),v.default.createElement(c.default,(0,i.default)({},P,S)))},P={};Object.keys(E.default).forEach(function(e){var t=E.default[e];P[e]=function(a){return v.default.createElement(y.default.Consumer,null,function(n){return v.default.createElement(x,(0,i.default)({customProps:t.props,rules:t.rules},a,{type:e},n,{updateActive:n.updateActive}))})}});var N=P;t.default=N},wqp7:function(e,t,a){"use strict";var n=a("fbTi"),r=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=r(a("zAuD"));a("N2W2");var u=r(a("5KpO")),o=n(a("2w0b")),c=r(a("YM0q")),i=u.default.TabPane,d=function(){var e=0;return function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return e+=1,"".concat(t).concat(e)}}(),s=function(e){(0,o.useEffect)(function(){var t=d("login-tab-"),a=e.tabUtil;a&&a.addTab(t)},[]);var t=e.children;return o.default.createElement(i,e,e.active&&t)},f=function(e){return o.default.createElement(c.default.Consumer,null,function(t){return o.default.createElement(s,(0,l.default)({tabUtil:t.tabUtil},e))})};f.typeName="LoginTab";var p=f;t.default=p},zzaQ:function(e,t,a){"use strict";var n=a("fbTi"),r=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("Ln34");var l=r(a("dtSJ")),u=r(a("mK77")),o=r(a("cO38"));a("2aCj");var c=r(a("KFC0")),i=a("vGWC"),d=n(a("2w0b")),s=a("/ssf"),f=a("LneV"),p=r(a("K4Vm")),m=r(a("TVJM")),g=p.default.Tab,v=p.default.UserName,h=p.default.Password,b=p.default.Mobile,E=p.default.Captcha,y=p.default.Submit,C=function(e){var t=e.content;return d.default.createElement(c.default,{style:{marginBottom:24},message:t,type:"error",showIcon:!0})},w=function(e){var t=e.userLogin,a=void 0===t?{}:t,n=e.submitting,r=a.status,c=a.type,f=(0,d.useState)(!0),w=(0,o.default)(f,2),T=w[0],x=w[1],P=(0,d.useState)("account"),N=(0,o.default)(P,2),O=N[0],U=N[1],k=function(t){var a=e.dispatch;a({type:"login/login",payload:(0,u.default)({},t,{type:O})})};return d.default.createElement("div",{className:m.default.main},d.default.createElement(p.default,{activeKey:O,onTabChange:U,onSubmit:k},d.default.createElement(g,{key:"account",tab:"\u8d26\u6237\u5bc6\u7801\u767b\u5f55"},"error"===r&&"account"===c&&!n&&d.default.createElement(C,{content:"\u8d26\u6237\u6216\u5bc6\u7801\u9519\u8bef\uff08admin/ant.design\uff09"}),d.default.createElement(v,{name:"userName",placeholder:"\u7528\u6237\u540d: admin or user",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u7528\u6237\u540d!"}]}),d.default.createElement(h,{name:"password",placeholder:"\u5bc6\u7801: ant.design",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u5bc6\u7801\uff01"}]})),d.default.createElement(g,{key:"mobile",tab:"\u624b\u673a\u53f7\u767b\u5f55"},"error"===r&&"mobile"===c&&!n&&d.default.createElement(C,{content:"\u9a8c\u8bc1\u7801\u9519\u8bef"}),d.default.createElement(b,{name:"mobile",placeholder:"\u624b\u673a\u53f7",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u624b\u673a\u53f7\uff01"},{pattern:/^1\d{10}$/,message:"\u624b\u673a\u53f7\u683c\u5f0f\u9519\u8bef\uff01"}]}),d.default.createElement(E,{name:"captcha",placeholder:"\u9a8c\u8bc1\u7801",countDown:120,getCaptchaButtonText:"",getCaptchaSecondText:"\u79d2",rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801\uff01"}]})),d.default.createElement("div",null,d.default.createElement(l.default,{checked:T,onChange:function(e){return x(e.target.checked)}},"\u81ea\u52a8\u767b\u5f55"),d.default.createElement("a",{style:{float:"right"}},"\u5fd8\u8bb0\u5bc6\u7801")),d.default.createElement(y,{loading:n},"\u767b\u5f55"),d.default.createElement("div",{className:m.default.other},"\u5176\u4ed6\u767b\u5f55\u65b9\u5f0f",d.default.createElement(i.AlipayCircleOutlined,{className:m.default.icon}),d.default.createElement(i.TaobaoCircleOutlined,{className:m.default.icon}),d.default.createElement(i.WeiboCircleOutlined,{className:m.default.icon}),d.default.createElement(s.Link,{className:m.default.register,to:"/user/register"},"\u6ce8\u518c\u8d26\u6237"))))},T=(0,f.connect)(function(e){var t=e.login,a=e.loading;return{userLogin:t,submitting:a.effects["login/login"]}})(w);t.default=T}}]);