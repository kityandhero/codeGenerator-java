package com.lzt.operate.utility.utils;

/**
 * @author luzhitao
 */
public enum Mime {
	/**
	 * Any mime：.*=application/octet-stream
	 */
	Any("Any", ".*", "application/octet-stream", new String[]{}),

	/**
	 * Status001 mime：.001=application/x-001
	 */
	Status001("Status001", ".001", "application/x-001", new String[]{}),

	/**
	 * Status301 mime：.301=application/x-301
	 */
	Status301("Status301", ".301", "application/x-301", new String[]{}),

	/**
	 * Status323 mime：.323=text/h323
	 */
	Status323("Status323", ".323", "text/h323", new String[]{}),

	/**
	 * Status906 mime：.906=application/x-906
	 */
	Status906("Status906", ".906", "application/x-906", new String[]{}),

	/**
	 * Status907 mime：.907=drawing/907
	 */
	Status907("Status907", ".907", "drawing/907", new String[]{}),

	/**
	 * A11 mime：.a11=application/x-a11
	 */
	A11("A11", ".a11", "application/x-a11", new String[]{}),

	/**
	 * Acp mime：.acp=audio/x-mei-aac
	 */
	Acp("Acp", ".acp", "audio/x-mei-aac", new String[]{}),

	/**
	 * Ai mime：.ai=application/postscript
	 */
	Ai("Ai", ".ai", "application/postscript", new String[]{}),

	/**
	 * Aif mime：.aif=audio/aiff
	 */
	Aif("Aif", ".aif", "audio/aiff", new String[]{}),

	/**
	 * Aifc mime：.aifc=audio/aiff
	 */
	Aifc("Aifc", ".aifc", "audio/aiff", new String[]{}),

	/**
	 * Aiff mime：.aiff=audio/aiff
	 */
	Aiff("Aiff", ".aiff", "audio/aiff", new String[]{}),

	/**
	 * Anv mime：.anv=application/x-anv
	 */
	Anv("Anv", ".anv", "application/x-anv", new String[]{}),

	/**
	 * Asa mime：.asa=text/asa
	 */
	Asa("Asa", ".asa", "text/asa", new String[]{}),

	/**
	 * Asf mime：.asf=video/x-ms-asf
	 */
	Asf("Asf", ".asf", "video/x-ms-asf", new String[]{}),

	/**
	 * Asp mime：.asp=text/asp
	 */
	Asp("Asp", ".asp", "text/asp", new String[]{}),

	/**
	 * Asx mime：.asx=video/x-ms-asf
	 */
	Asx("Asx", ".asx", "video/x-ms-asf", new String[]{}),

	/**
	 * Au mime：.au=audio/basic
	 */
	Au("Au", ".au", "audio/basic", new String[]{}),

	/**
	 * Avi mime：.avi=video/avi
	 */
	Avi("Avi", ".avi", "video/avi", new String[]{}),

	/**
	 * Awf mime：.awf=application/vnd.adobe.workflow
	 */
	Awf("Awf", ".awf", "application/vnd.adobe.workflow", new String[]{}),

	/**
	 * Biz mime：.biz=text/xml
	 */
	Biz("Biz", ".biz", "text/xml", new String[]{}),

	/**
	 * Bmp mime：.bmp=application/x-bmp
	 */
	Bmp("Bmp", ".bmp", "application/x-bmp", new String[]{}),

	/**
	 * Bot mime：.bot=application/x-bot
	 */
	Bot("Bot", ".bot", "application/x-bot", new String[]{}),

	/**
	 * C4t mime：.c4t=application/x-c4t
	 */
	C4T("C4t", ".c4t", "application/x-c4t", new String[]{}),

	/**
	 * C90 mime：.c90=application/x-c90
	 */
	C90("C90", ".c90", "application/x-c90", new String[]{}),

	/**
	 * Cal mime：.cal=application/x-cals
	 */
	Cal("Cal", ".cal", "application/x-cals", new String[]{}),

	/**
	 * Cat mime：.cat=application/s-pki.seccat
	 */
	Cat("Cat", ".cat", "application/s-pki.seccat", new String[]{}),

	/**
	 * Cdf mime：.cdf=application/x-netcdf
	 */
	Cdf("Cdf", ".cdf", "application/x-netcdf", new String[]{}),

	/**
	 * Cdr mime：.cdr=application/x-cdr
	 */
	Cdr("Cdr", ".cdr", "application/x-cdr", new String[]{}),

	/**
	 * Cel mime：.cel=application/x-cel
	 */
	Cel("Cel", ".cel", "application/x-cel", new String[]{}),

	/**
	 * Cer mime：.cer=application/x-x509-ca-cert
	 */
	Cer("Cer", ".cer", "application/x-x509-ca-cert", new String[]{}),

	/**
	 * Cg4 mime：.cg4=application/x-g4
	 */
	Cg4("Cg4", ".cg4", "application/x-g4", new String[]{}),

	/**
	 * Cgm mime：.cgm=application/x-cgm
	 */
	Cgm("Cgm", ".cgm", "application/x-cgm", new String[]{}),

	/**
	 * Cit mime：.cit=application/x-cit
	 */
	Cit("Cit", ".cit", "application/x-cit", new String[]{}),

	/**
	 * Class mime：.class=java/*
	 */
	Class("Class", ".class", "java/*", new String[]{}),

	/**
	 * Cml mime：.cml=text/xml
	 */
	Cml("Cml", ".cml", "text/xml", new String[]{}),

	/**
	 * Cmp mime：.cmp=application/x-cmp
	 */
	Cmp("Cmp", ".cmp", "application/x-cmp", new String[]{}),

	/**
	 * Cmx mime：.cmx=application/x-cmx
	 */
	Cmx("Cmx", ".cmx", "application/x-cmx", new String[]{}),

	/**
	 * Cot mime：.cot=application/x-cot
	 */
	Cot("Cot", ".cot", "application/x-cot", new String[]{}),

	/**
	 * Crl mime：.crl=application/pkix-crl
	 */
	Crl("Crl", ".crl", "application/pkix-crl", new String[]{}),

	/**
	 * Crt mime：.crt=application/x-x509-ca-cert
	 */
	Crt("Crt", ".crt", "application/x-x509-ca-cert", new String[]{}),

	/**
	 * Csi mime：.csi=application/x-csi
	 */
	Csi("Csi", ".csi", "application/x-csi", new String[]{}),

	/**
	 * Css mime：.css=text/css
	 */
	Css("Css", ".css", "text/css", new String[]{}),

	/**
	 * Cut mime：.cut=application/x-cut
	 */
	Cut("Cut", ".cut", "application/x-cut", new String[]{}),

	/**
	 * Dbf mime：.dbf=application/x-dbf
	 */
	Dbf("Dbf", ".dbf", "application/x-dbf", new String[]{}),

	/**
	 * Dbm mime：.dbm=application/x-dbm
	 */
	Dbm("Dbm", ".dbm", "application/x-dbm", new String[]{}),

	/**
	 * Dbx mime：.dbx=application/x-dbx
	 */
	Dbx("Dbx", ".dbx", "application/x-dbx", new String[]{}),

	/**
	 * Dcd mime：.dcd=text/xml
	 */
	Dcd("Dcd", ".dcd", "text/xml", new String[]{}),

	/**
	 * Dcx mime：.dcx=application/x-dcx
	 */
	Dcx("Dcx", ".dcx", "application/x-dcx", new String[]{}),

	/**
	 * Der mime：.der=application/x-x509-ca-cert
	 */
	Der("Der", ".der", "application/x-x509-ca-cert", new String[]{}),

	/**
	 * Dgn mime：.dgn=application/x-dgn
	 */
	Dgn("Dgn", ".dgn", "application/x-dgn", new String[]{}),

	/**
	 * Dib mime：.dib=application/x-dib
	 */
	Dib("Dib", ".dib", "application/x-dib", new String[]{}),

	/**
	 * Dll mime：.dll=application/x-msdownload
	 */
	Dll("Dll", ".dll", "application/x-msdownload", new String[]{}),

	/**
	 * Doc mime：.doc=application/msword
	 */
	Doc("Doc", ".doc", "application/msword", new String[]{}),

	/**
	 * Dot mime：.dot=application/msword
	 */
	Dot("Dot", ".dot", "application/msword", new String[]{}),

	/**
	 * Drw mime：.drw=application/x-drw
	 */
	Drw("Drw", ".drw", "application/x-drw", new String[]{}),

	/**
	 * Dtd mime：.dtd=text/xml
	 */
	Dtd("Dtd", ".dtd", "text/xml", new String[]{}),

	/**
	 * Dwf mime：.dwf=Model/vnd.dwf， .dwf=application/x-dwf
	 */
	Dwf("Dwf", ".dwf", "Model/vnd.dwf", new String[]
			{
					"application/x-dwf",
			}),

	/**
	 * Dwg mime：.dwg=application/x-dwg
	 */
	Dwg("Dwg", ".dwg", "application/x-dwg", new String[]{}),

	/**
	 * Dxb mime：.dxb=application/x-dxb
	 */
	Dxb("Dxb", ".dxb", "application/x-dxb", new String[]{}),

	/**
	 * Dxf mime：.dxf=application/x-dxf
	 */
	Dxf("Dxf", ".dxf", "application/x-dxf", new String[]{}),

	/**
	 * Edn mime：.edn=application/vnd.adobe.edn
	 */
	Edn("Edn", ".edn", "application/vnd.adobe.edn", new String[]{}),

	/**
	 * Emf mime：.emf=application/x-emf
	 */
	Emf("Emf", ".emf", "application/x-emf", new String[]{}),

	/**
	 * Eml mime：.eml=message/rfc822
	 */
	Eml("Eml", ".eml", "message/rfc822", new String[]{}),

	/**
	 * Ent mime：.ent=text/xml
	 */
	Ent("Ent", ".ent", "text/xml", new String[]{}),

	/**
	 * Epi mime：.epi=application/x-epi
	 */
	Epi("Epi", ".epi", "application/x-epi", new String[]{}),

	/**
	 * Eps mime：.eps=application/x-ps，.eps=application/postscript
	 */
	Eps("Eps", ".eps", "application/x-ps", new String[]
			{
					"application/postscript"
			}),

	/**
	 * Etd mime：.etd=application/x-ebx
	 */
	Etd("Etd", ".etd", "application/x-ebx", new String[]{}),

	/**
	 * Exe mime：.exe=application/x-msdownload
	 */
	Exe("Exe", ".exe", "application/x-msdownload", new String[]{}),

	/**
	 * Fax mime：.fax=image/fax
	 */
	Fax("Fax", ".fax", "image/fax", new String[]{}),

	/**
	 * Fdf mime：.fdf=application/vnd.fdf
	 */
	Fdf("Fdf", ".fdf", "application/vnd.fdf", new String[]{}),

	/**
	 * Fif mime：.fif=application/fractals
	 */
	Fif("Fif", ".fif", "application/fractals", new String[]{}),

	/**
	 * Fo mime：.fo=text/xml
	 */
	Fo("Fo", ".fo", "text/xml", new String[]{}),

	/**
	 * Frm mime：.frm=application/x-frm
	 */
	Frm("Frm", ".frm", "application/x-frm", new String[]{}),

	/**
	 * Frm mime：application/x-www-form-urlencoded
	 */
	FormUrlencoded("FormUrlencoded", "", "application/x-www-form-urlencoded", new String[]{}),

	/**
	 * Frm mime：multipart/form-data
	 */
	FormData("FormData", "", "multipart/form-data", new String[]{}),

	/**
	 * G4 mime：.g4=application/x-g4
	 */
	G4("G4", ".g4", "application/x-g4", new String[]{}),

	/**
	 * Gbr mime：.gbr=application/x-gbr
	 */
	Gbr("Gbr", ".gbr", "application/x-gbr", new String[]{}),

	/**
	 * Gcd mime：.gcd=application/x-gcd
	 */
	Gcd("Gcd", ".gcd", "application/x-gcd", new String[]{}),

	/**
	 * Gif mime：.gif=image/gif
	 */
	Gif("Gif", ".gif", "image/gif", new String[]{}),

	/**
	 * Gl2 mime：.gl2=application/x-gl2
	 */
	Gl2("Gl2", ".gl2", "application/x-gl2", new String[]{}),

	/**
	 * Gp4 mime：.gp4=application/x-gp4
	 */
	Gp4("Gp4", ".gp4", "application/x-gp4", new String[]{}),

	/**
	 * Hgl mime：.hgl=application/x-hgl
	 */
	Hgl("Hgl", ".hgl", "application/x-hgl", new String[]{}),

	/**
	 * Hmr mime：.hmr=application/x-hmr
	 */
	Hmr("Hmr", ".hmr", "application/x-hmr", new String[]{}),

	/**
	 * Hpg mime：.hpg=application/x-hpgl
	 */
	Hpg("Hpg", ".hpg", "application/x-hpgl", new String[]{}),

	/**
	 * Hpl mime：.hpl=application/x-hpl
	 */
	Hpl("Hpl", ".hpl", "application/x-hpl", new String[]{}),

	/**
	 * Hqx mime：.hqx=application/mac-binhex40
	 */
	Hqx("Hqx", ".hqx", "application/mac-binhex40", new String[]{}),

	/**
	 * Hrf mime：.hrf=application/x-hrf
	 */
	Hrf("Hrf", ".hrf", "application/x-hrf", new String[]{}),

	/**
	 * Hta mime：.hta=application/hta
	 */
	Hta("Hta", ".hta", "application/hta", new String[]{}),

	/**
	 * Htc mime：.htc=text/x-component
	 */
	Htc("Htc", ".htc", "text/x-component", new String[]{}),

	/**
	 * Htm mime：.htm=text/html
	 */
	Htm("Htm", ".htm", "text/html", new String[]{}),

	/**
	 * Html mime：.html=text/html
	 */
	Html("Html", ".html", "text/html", new String[]{}),

	/**
	 * Htt mime：.htt=text/webviewhtml
	 */
	Htt("Htt", ".htt", "text/webviewhtml", new String[]{}),

	/**
	 * Htx mime：.htx=text/html
	 */
	Htx("Htx", ".htx", "text/html", new String[]{}),

	/**
	 * Icb mime：.icb=application/x-icb
	 */
	Icb("Icb", ".icb", "application/x-icb", new String[]{}),

	/**
	 * Ico mime：.ico=image/x-icon，.ico=application/x-ico
	 */
	Ico("Ico", ".ico", "image/x-icon", new String[]
			{
					"application/x-ico",
			}),

	/**
	 * Iff mime：.iff=application/x-iff
	 */
	Iff("Iff", ".iff", "application/x-iff", new String[]{}),

	/**
	 * Ig4 mime：.ig4=application/x-g4
	 */
	Ig4("Ig4", ".ig4", "application/x-g4", new String[]{}),

	/**
	 * Igs mime：.igs=application/x-igs
	 */
	Igs("Igs", ".igs", "application/x-igs", new String[]{}),

	/**
	 * Iii mime：.iii=application/x-iphone
	 */
	Iii("Iii", ".iii", "application/x-iphone", new String[]{}),

	/**
	 * Img mime：.img=application/x-img
	 */
	Img("Img", ".img", "application/x-img", new String[]{}),

	/**
	 * Ins mime：.ins=application/x-internet-signup
	 */
	Ins("Ins", ".ins", "application/x-internet-signup", new String[]{}),

	/**
	 * Isp mime：.isp=application/x-internet-signup
	 */
	Isp("Isp", ".isp", "application/x-internet-signup", new String[]{}),

	/**
	 * IVF mime：.IVF=video/x-ivf
	 */
	Ivf("IVF", ".IVF", "video/x-ivf", new String[]{}),

	/**
	 * Java mime：.java=java/*
	 */
	Java("Java", ".java", "java/*", new String[]{}),

	/**
	 * Jfif mime：.jfif=image/jpeg
	 */
	Jfif("Jfif", ".jfif", "image/jpeg", new String[]{}),

	/**
	 * Jpe mime：.jpe=image/jpeg，.jpe=application/x-jpe
	 */
	Jpe("Jpe", ".jpe", "image/jpeg", new String[]
			{
					"application/x-jpe",
			}),

	/**
	 * Jpeg mime：.jpeg=image/jpeg，.jpg=image/jpeg
	 */
	Jpeg("Jpeg", ".jpeg", "image/jpeg", new String[]
			{
					"image/jpeg",
			}),

	/**
	 * Jpg mime：.jpg=application/x-jpg
	 */
	Jpg("Jpg", ".jpg", "application/x-jpg", new String[]{}),

	/**
	 * Js mime：.js=application/x-javascript
	 */
	Js("Js", ".js", "application/x-javascript", new String[]{}),

	/**
	 * Jsp mime：.jsp=text/html
	 */
	Jsp("Jsp", ".jsp", "text/html", new String[]{}),

	/**
	 * Json mime：.js=application/json
	 */
	Json("Json", ".js", "application/json", new String[]{}),

	/**
	 * La1 mime：.la1=audio/x-liquid-file
	 */
	La1("La1", ".la1", "audio/x-liquid-file", new String[]{}),

	/**
	 * Lar mime：.lar=application/x-laplayer-reg
	 */
	Lar("Lar", ".lar", "application/x-laplayer-reg", new String[]{}),

	/**
	 * Latex mime：.latex=application/x-latex
	 */
	Latex("Latex", ".latex", "application/x-latex", new String[]{}),

	/**
	 * Lavs mime：.lavs=audio/x-liquid-secure
	 */
	Lavs("Lavs", ".lavs", "audio/x-liquid-secure", new String[]{}),

	/**
	 * Lbm mime：.lbm=application/x-lbm
	 */
	Lbm("Lbm", ".lbm", "application/x-lbm", new String[]{}),

	/**
	 * Lmsff mime：.lmsff=audio/x-la-lms
	 */
	Lmsff("Lmsff", ".lmsff", "audio/x-la-lms", new String[]{}),

	/**
	 * Ls mime：.ls=application/x-javascript
	 */
	Ls("Ls", ".ls", "application/x-javascript", new String[]{}),

	/**
	 * Ltr mime：.ltr=application/x-ltr
	 */
	Ltr("Ltr", ".ltr", "application/x-ltr", new String[]{}),

	/**
	 * M1v mime：.m1v=video/x-mpeg
	 */
	M1V("M1v", ".m1v", "video/x-mpeg", new String[]{}),

	/**
	 * M2v mime：.m2v=video/x-mpeg
	 */
	M2V("M2v", ".m2v", "video/x-mpeg", new String[]{}),

	/**
	 * M3u mime：.m3u=audio/mpegurl
	 */
	M3U("M3u", ".m3u", "audio/mpegurl", new String[]{}),

	/**
	 * M4e mime：.m4e=video/mpeg4
	 */
	M4E("M4e", ".m4e", "video/mpeg4", new String[]{}),

	/**
	 * Mac mime：.mac=application/x-mac
	 */
	Mac("Mac", ".mac", "application/x-mac", new String[]{}),

	/**
	 * Man mime：.man=application/x-troff-man
	 */
	Man("Man", ".man", "application/x-troff-man", new String[]{}),

	/**
	 * Math mime：.math=text/xml
	 */
	Math("Math", ".math", "text/xml", new String[]{}),

	/**
	 * Mdb mime：.mdb=application/msaccess，.mdb=application/x-mdb
	 */
	Mdb("Mdb", ".mdb", "application/msaccess", new String[]
			{
					"application/x-mdb",
			}),

	/**
	 * Mfp mime：.mfp=application/x-shockwave-flash
	 */
	Mfp("Mfp", ".mfp", "application/x-shockwave-flash", new String[]{}),

	/**
	 * Mht mime：.mht=message/rfc822
	 */
	Mht("Mht", ".mht", "message/rfc822", new String[]{}),

	/**
	 * Mhtml mime：.mhtml=message/rfc822
	 */
	Mhtml("Mhtml", ".mhtml", "message/rfc822", new String[]{}),

	/**
	 * Mi mime：.mi=application/x-mi
	 */
	Mi("Mi", ".mi", "application/x-mi", new String[]{}),

	/**
	 * Mid mime：.mid=audio/mid
	 */
	Mid("Mid", ".mid", "audio/mid", new String[]{}),

	/**
	 * Midi mime：.midi=audio/mid
	 */
	Midi("Midi", ".midi", "audio/mid", new String[]{}),

	/**
	 * Mil mime：.mil=application/x-mil
	 */
	Mil("Mil", ".mil", "application/x-mil", new String[]{}),

	/**
	 * Mml mime：.mml=text/xml
	 */
	Mml("Mml", ".mml", "text/xml", new String[]{}),

	/**
	 * Mnd mime：.mnd=audio/x-musicnet-download
	 */
	Mnd("Mnd", ".mnd", "audio/x-musicnet-download", new String[]{}),

	/**
	 * Mns mime：.mns=audio/x-musicnet-stream
	 */
	Mns("Mns", ".mns", "audio/x-musicnet-stream", new String[]{}),

	/**
	 * Mocha mime：.mocha=application/x-javascript
	 */
	Mocha("Mocha", ".mocha", "application/x-javascript", new String[]{}),

	/**
	 * Movie mime：.movie=video/x-sgi-movie
	 */
	Movie("Movie", ".movie", "video/x-sgi-movie", new String[]{}),

	/**
	 * Mp1 mime：.mp1=audio/mp1
	 */
	Mp1("Mp1", ".mp1", "audio/mp1", new String[]{}),

	/**
	 * Mp2 mime：.mp2=audio/mp2
	 */
	Mp2("Mp2", ".mp2", "audio/mp2", new String[]{}),

	/**
	 * Mp2v mime：.mp2v=video/mpeg
	 */
	Mp2V("Mp2v", ".mp2v", "video/mpeg", new String[]{}),

	/**
	 * Mp3 mime：.mp3=audio/mp3
	 */
	Mp3("Mp3", ".mp3", "audio/mp3", new String[]{}),

	/**
	 * Mp4 mime：.mp4=video/mp4
	 */
	Mp4("Mp4", ".mp4", "video/mp4", new String[]{}),

	/**
	 * Mpa mime：.mpa=video/x-mpg
	 */
	Mpa("Mpa", ".mpa", "video/x-mpg", new String[]{}),

	/**
	 * Mpd mime：.mpd=application/-project
	 */
	Mpd("Mpd", ".mpd", "application/-project", new String[]{}),

	/**
	 * Mpe mime：.mpe=video/x-mpeg
	 */
	Mpe("Mpe", ".mpe", "video/x-mpeg", new String[]{}),

	/**
	 * Mpeg mime：.mpeg=video/mpg
	 */
	Mpeg("Mpeg", ".mpeg", "video/mpg", new String[]{}),

	/**
	 * Mpg mime：.mpg=video/mpg
	 */
	Mpg("Mpg", ".mpg", "video/mpg", new String[]{}),

	/**
	 * Mpga mime：.mpga=audio/rn-mpeg
	 */
	Mpga("Mpga", ".mpga", "audio/rn-mpeg", new String[]{}),

	/**
	 * Mpp mime：.mpp=application/-project
	 */
	Mpp("Mpp", ".mpp", "application/-project", new String[]{}),

	/**
	 * Mps mime：.mps=video/x-mpeg
	 */
	Mps("Mps", ".mps", "video/x-mpeg", new String[]{}),

	/**
	 * Mpt mime：.mpt=application/-project
	 */
	Mpt("Mpt", ".mpt", "application/-project", new String[]{}),

	/**
	 * Mpv mime：.mpv=video/mpg
	 */
	Mpv("Mpv", ".mpv", "video/mpg", new String[]{}),

	/**
	 * Mpv2 mime：.mpv2=video/mpeg
	 */
	Mpv2("Mpv2", ".mpv2", "video/mpeg", new String[]{}),

	/**
	 * Mpw mime：.mpw=application/s-project
	 */
	Mpw("Mpw", ".mpw", "application/s-project", new String[]{}),

	/**
	 * Mpx mime：.mpx=application/-project
	 */
	Mpx("Mpx", ".mpx", "application/-project", new String[]{}),

	/**
	 * Mtx mime：.mtx=text/xml
	 */
	Mtx("Mtx", ".mtx", "text/xml", new String[]{}),

	/**
	 * Mxp mime：.mxp=application/x-mmxp
	 */
	Mxp("Mxp", ".mxp", "application/x-mmxp", new String[]{}),

	/**
	 * Net mime：.net=image/pnetvue
	 */
	Net("Net", ".net", "image/pnetvue", new String[]{}),

	/**
	 * Nrf mime：.nrf=application/x-nrf
	 */
	Nrf("Nrf", ".nrf", "application/x-nrf", new String[]{}),

	/**
	 * Nws mime：.nws=message/rfc822
	 */
	Nws("Nws", ".nws", "message/rfc822", new String[]{}),

	/**
	 * Odc mime：.odc=text/x-ms-odc
	 */
	Odc("Odc", ".odc", "text/x-ms-odc", new String[]{}),

	/**
	 * Out mime：.out=application/x-out
	 */
	Out("Out", ".out", "application/x-out", new String[]{}),

	/**
	 * P10 mime：.p10=application/pkcs10
	 */
	P10("P10", ".p10", "application/pkcs10", new String[]{}),

	/**
	 * P12 mime：.p12=application/x-pkcs12
	 */
	P12("P12", ".p12", "application/x-pkcs12", new String[]{}),

	/**
	 * P7b mime：.p7b=application/x-pkcs7-certificates
	 */
	P7B("P7B", ".p7b", "application/x-pkcs7-certificates", new String[]{}),

	/**
	 * P7c mime：.p7c=application/pkcs7-mime
	 */
	P7C("P7C", ".p7c", "application/pkcs7-mime", new String[]{}),

	/**
	 * P7m mime：.p7m=application/pkcs7-mime
	 */
	P7M("P7M", ".p7m", "application/pkcs7-mime", new String[]{}),

	/**
	 * P7r mime：.p7r=application/x-pkcs7-certreqresp
	 */
	P7R("P7R", ".p7r", "application/x-pkcs7-certreqresp", new String[]{}),

	/**
	 * P7s mime：.p7s=application/pkcs7-signature
	 */
	P7S("P7S", ".p7s", "application/pkcs7-signature", new String[]{}),

	/**
	 * Pc5 mime：.pc5=application/x-pc5
	 */
	Pc5("Pc5", ".pc5", "application/x-pc5", new String[]{}),

	/**
	 * Pci mime：.pci=application/x-pci
	 */
	Pci("Pci", ".pci", "application/x-pci", new String[]{}),

	/**
	 * Pcl mime：.pcl=application/x-pcl
	 */
	Pcl("Pcl", ".pcl", "application/x-pcl", new String[]{}),

	/**
	 * Pcx mime：.pcx=application/x-pcx
	 */
	Pcx("Pcx", ".pcx", "application/x-pcx", new String[]{}),

	/**
	 * Pdf mime：.pdf=application/pdf
	 */
	Pdf("Pdf", ".pdf", "application/pdf", new String[]{}),

	/**
	 * Pdx mime：.pdx=application/vnd.adobe.pdx
	 */
	Pdx("Pdx", ".pdx", "application/vnd.adobe.pdx", new String[]{}),

	/**
	 * Pfx mime：.pfx=application/x-pkcs12
	 */
	Pfx("Pfx", ".pfx", "application/x-pkcs12", new String[]{}),

	/**
	 * Pgl mime：.pgl=application/x-pgl
	 */
	Pgl("Pgl", ".pgl", "application/x-pgl", new String[]{}),

	/**
	 * Pic mime：.pic=application/x-pic
	 */
	Pic("Pic", ".pic", "application/x-pic", new String[]{}),

	/**
	 * Pko mime：.pko=application-pki.pko
	 */
	Pko("Pko", ".pko", "application-pki.pko", new String[]{}),

	/**
	 * Pl mime：.pl=application/x-perl
	 */
	Pl("Pl", ".pl", "application/x-perl", new String[]{}),

	/**
	 * Plg mime：.plg=text/html
	 */
	Plg("Plg", ".plg", "text/html", new String[]{}),

	/**
	 * Pls mime：.pls=audio/scpls
	 */
	Pls("Pls", ".pls", "audio/scpls", new String[]{}),

	/**
	 * Plt mime：.plt=application/x-plt
	 */
	Plt("Plt", ".plt", "application/x-plt", new String[]{}),

	/**
	 * Png mime：.png=image/png，.png=application/x-png
	 */
	Png("Png", ".png", "image/png", new String[]
			{
					"application/x-png",
			}),

	/**
	 * Pot mime：.pot=applications-powerpoint
	 */
	Pot("Pot", ".pot", "applications-powerpoint", new String[]{}),

	/**
	 * Ppa mime：.ppa=application/vs-powerpoint
	 */
	Ppa("Ppa", ".ppa", "application/vs-powerpoint", new String[]{}),

	/**
	 * Ppm mime：.ppm=application/x-ppm
	 */
	Ppm("Ppm", ".ppm", "application/x-ppm", new String[]{}),

	/**
	 * Pps mime：.pps=application-powerpoint
	 */
	Pps("Pps", ".pps", "application-powerpoint", new String[]{}),

	/**
	 * Ppt mime：.ppt=applications-powerpoint，.ppt=application/x-ppt
	 */
	Ppt("Ppt", ".ppt", "applications-powerpoint", new String[]
			{
					"application/x-ppt",
			}),

	/**
	 * Pr mime：.pr=application/x-pr
	 */
	Pr("Pr", ".pr", "application/x-pr", new String[]{}),

	/**
	 * Prf mime：.prf=application/pics-rules
	 */
	Prf("Prf", ".prf", "application/pics-rules", new String[]{}),

	/**
	 * Prn mime：.prn=application/x-prn
	 */
	Prn("Prn", ".prn", "application/x-prn", new String[]{}),

	/**
	 * Prt mime：.prt=application/x-prt
	 */
	Prt("Prt", ".prt", "application/x-prt", new String[]{}),

	/**
	 * Ps mime：.ps=application/x-ps，.ps=application/postscript
	 */
	Ps("Ps", ".ps", "application/x-ps", new String[]
			{
					"application/postscript",
			}),

	/**
	 * Ptn mime：.ptn=application/x-ptn
	 */
	Ptn("Ptn", ".ptn", "application/x-ptn", new String[]{}),

	/**
	 * Pwz mime：.pwz=application/powerpoint
	 */
	Pwz("Pwz", ".pwz", "application/powerpoint", new String[]{}),

	/**
	 * R3t mime：.r3t=text/vnd.rn-realtext3d
	 */
	R3T("R3T", ".r3t", "text/vnd.rn-realtext3d", new String[]{}),

	/**
	 * Ra mime：.ra=audio/vnd.rn-realaudio
	 */
	Ra("Ra", ".ra", "audio/vnd.rn-realaudio", new String[]{}),

	/**
	 * Ram mime：.ram=audio/x-pn-realaudio
	 */
	Ram("Ram", ".ram", "audio/x-pn-realaudio", new String[]{}),

	/**
	 * Ras mime：.ras=application/x-ras
	 */
	Ras("Ras", ".ras", "application/x-ras", new String[]{}),

	/**
	 * Rat mime：.rat=application/rat-file
	 */
	Rat("Rat", ".rat", "application/rat-file", new String[]{}),

	/**
	 * Rdf mime：.rdf=text/xml
	 */
	Rdf("Rdf", ".rdf", "text/xml", new String[]{}),

	/**
	 * Rec mime：.rec=application/vnd.rn-recording
	 */
	Rec("Rec", ".rec", "application/vnd.rn-recording", new String[]{}),

	/**
	 * Red mime：.red=application/x-red
	 */
	Red("Red", ".red", "application/x-red", new String[]{}),

	/**
	 * Rgb mime：.rgb=application/x-rgb
	 */
	Rgb("Rgb", ".rgb", "application/x-rgb", new String[]{}),

	/**
	 * Rjs mime：.rjs=application/vnd.rn-realsystem-rjs
	 */
	Rjs("Rjs", ".rjs", "application/vnd.rn-realsystem-rjs", new String[]{}),

	/**
	 * Rjt mime：.rjt=application/vnd.rn-realsystem-rjt
	 */
	Rjt("Rjt", ".rjt", "application/vnd.rn-realsystem-rjt", new String[]{}),

	/**
	 * Rlc mime：.rlc=application/x-rlc
	 */
	Rlc("Rlc", ".rlc", "application/x-rlc", new String[]{}),

	/**
	 * Rle mime：.rle=application/x-rle
	 */
	Rle("Rle", ".rle", "application/x-rle", new String[]{}),

	/**
	 * Rm mime：.rm=application/vnd.rn-realmedia
	 */
	Rm("Rm", ".rm", "application/vnd.rn-realmedia", new String[]{}),

	/**
	 * Rmf mime：.rmf=application/vnd.adobe.rmf
	 */
	Rmf("Rmf", ".rmf", "application/vnd.adobe.rmf", new String[]{}),

	/**
	 * Rmi mime：.rmi=audio/mid
	 */
	Rmi("Rmi", ".rmi", "audio/mid", new String[]{}),

	/**
	 * Rmj mime：.rmj=application/vnd.rn-realsystem-rmj
	 */
	Rmj("Rmj", ".rmj", "application/vnd.rn-realsystem-rmj", new String[]{}),

	/**
	 * Rmm mime：.rmm=audio/x-pn-realaudio
	 */
	Rmm("Rmm", ".rmm", "audio/x-pn-realaudio", new String[]{}),

	/**
	 * Rmp mime：.rmp=application/vnd.rn-rn_music_package
	 */
	Rmp("Rmp", ".rmp", "application/vnd.rn-rn_music_package", new String[]{}),

	/**
	 * Rms mime：.rms=application/vnd.rn-realmedia-secure
	 */
	Rms("Rms", ".rms", "application/vnd.rn-realmedia-secure", new String[]{}),

	/**
	 * Rmvb mime：.rmvb=application/vnd.rn-realmedia-vbr
	 */
	Rmvb("Rmvb", ".rmvb", "application/vnd.rn-realmedia-vbr", new String[]{}),

	/**
	 * Rmx mime：.rmx=application/vnd.rn-realsystem-rmx
	 */
	Rmx("Rmx", ".rmx", "application/vnd.rn-realsystem-rmx", new String[]{}),

	/**
	 * Rnx mime：.rnx=application/vnd.rn-realplayer
	 */
	Rnx("Rnx", ".rnx", "application/vnd.rn-realplayer", new String[]{}),

	/**
	 * Rp mime：.rp=image/vnd.rn-realpix
	 */
	Rp("Rp", ".rp", "image/vnd.rn-realpix", new String[]{}),

	/**
	 * Rpm mime：.rpm=audio/x-pn-realaudio-plugin
	 */
	Rpm("Rpm", ".rpm", "audio/x-pn-realaudio-plugin", new String[]{}),

	/**
	 * Rsml mime：.rsml=application/vnd.rn-rsml
	 */
	Rsml("Rsml", ".rsml", "application/vnd.rn-rsml", new String[]{}),

	/**
	 * Rt mime：.rt=text/vnd.rn-realtext
	 */
	Rt("Rt", ".rt", "text/vnd.rn-realtext", new String[]{}),

	/**
	 * Rtf mime：.rtf=application/msword，.rtf=application/x-rtf
	 */
	Rtf("Rtf", ".rtf", "application/msword", new String[]
			{
					"application/x-rtf",
			}),

	/**
	 * Rv mime：.rv=video/vnd.rn-realvideo
	 */
	Rv("Rv", ".rv", "video/vnd.rn-realvideo", new String[]{}),

	/**
	 * Sam mime：.sam=application/x-sam
	 */
	Sam("Sam", ".sam", "application/x-sam", new String[]{}),

	/**
	 * Sat mime：.sat=application/x-sat
	 */
	Sat("Sat", ".sat", "application/x-sat", new String[]{}),

	/**
	 * Sdp mime：.sdp=application/sdp
	 */
	Sdp("Sdp", ".sdp", "application/sdp", new String[]{}),

	/**
	 * Sdw mime：.sdw=application/x-sdw
	 */
	Sdw("Sdw", ".sdw", "application/x-sdw", new String[]{}),

	/**
	 * Sit mime：.sit=application/x-stuffit
	 */
	Sit("Sit", ".sit", "application/x-stuffit", new String[]{}),

	/**
	 * Slb mime：.slb=application/x-slb
	 */
	Slb("Slb", ".slb", "application/x-slb", new String[]{}),

	/**
	 * Sld mime：.sld=application/x-sld
	 */
	Sld("Sld", ".sld", "application/x-sld", new String[]{}),

	/**
	 * Slk mime：.slk=drawing/x-slk
	 */
	Slk("Slk", ".slk", "drawing/x-slk", new String[]{}),

	/**
	 * Smi mime：.smi=application/smil
	 */
	Smi("Smi", ".smi", "application/smil", new String[]{}),

	/**
	 * Smil mime：.smil=application/smil
	 */
	Smil("Smil", ".smil", "application/smil", new String[]{}),

	/**
	 * Smk mime：.smk=application/x-smk
	 */
	Smk("Smk", ".smk", "application/x-smk", new String[]{}),

	/**
	 * Snd mime：.snd=audio/basic
	 */
	Snd("Snd", ".snd", "audio/basic", new String[]{}),

	/**
	 * Sol mime：.sol=text/plain
	 */
	Sol("Sol", ".sol", "text/plain", new String[]{}),

	/**
	 * Sor mime：.sor=text/plain
	 */
	Sor("Sor", ".sor", "text/plain", new String[]{}),

	/**
	 * Spc mime：.spc=application/x-pkcs7-certificates
	 */
	Spc("Spc", ".spc", "application/x-pkcs7-certificates", new String[]{}),

	/**
	 * Spl mime：.spl=application/futuresplash
	 */
	Spl("Spl", ".spl", "application/futuresplash", new String[]{}),

	/**
	 * Spp mime：.spp=text/xml
	 */
	Spp("Spp", ".spp", "text/xml", new String[]{}),

	/**
	 * Ssm mime：.ssm=application/streamingmedia
	 */
	Ssm("Ssm", ".ssm", "application/streamingmedia", new String[]{}),

	/**
	 * Sst mime：.sst=application-pki.certstore
	 */
	Sst("Sst", ".sst", "application-pki.certstore", new String[]{}),

	/**
	 * Stl mime：.stl=application/-pki.stl
	 */
	Stl("Stl", ".stl", "application/-pki.stl", new String[]{}),

	/**
	 * Stm mime：.stm=text/html
	 */
	Stm("Stm", ".stm", "text/html", new String[]{}),

	/**
	 * Sty mime：.sty=application/x-sty
	 */
	Sty("Sty", ".sty", "application/x-sty", new String[]{}),

	/**
	 * Svg mime：.svg=text/xml
	 */
	Svg("Svg", ".svg", "text/xml", new String[]{}),

	/**
	 * Swf mime：.swf=application/x-shockwave-flash
	 */
	Swf("Swf", ".swf", "application/x-shockwave-flash", new String[]{}),

	/**
	 * Tdf mime：.tdf=application/x-tdf
	 */
	Tdf("Tdf", ".tdf", "application/x-tdf", new String[]{}),

	/**
	 * Tg4 mime：.tg4=application/x-tg4
	 */
	Tg4("Tg4", ".tg4", "application/x-tg4", new String[]{}),

	/**
	 * Tga mime：.tga=application/x-tga
	 */
	Tga("Tga", ".tga", "application/x-tga", new String[]{}),

	/**
	 * Tif mime：.tif=image/tiff，.tif=application/x-tif
	 */
	Tif("Tif", ".tif", "image/tiff", new String[]
			{
					"application/x-tif",
			}),

	/**
	 * Tiff mime：.tiff=image/tiff
	 */
	Tiff("Tiff", ".tiff", "image/tiff", new String[]{}),

	/**
	 * Tld mime：.tld=text/xml
	 */
	Tld("Tld", ".tld", "text/xml", new String[]{}),

	/**
	 * Top mime：.top=drawing/x-top
	 */
	Top("Top", ".top", "drawing/x-top", new String[]{}),

	/**
	 * Torrent mime：.torrent=application/x-bittorrent
	 */
	Torrent("Torrent", ".torrent", "application/x-bittorrent", new String[]{}),

	/**
	 * Tsd mime：.tsd=text/xml
	 */
	Tsd("Tsd", ".tsd", "text/xml", new String[]{}),

	/**
	 * Txt mime：.txt=text/plain
	 */
	Txt("Txt", ".txt", "text/plain", new String[]{}),

	/**
	 * Uin mime：.uin=application/x-icq
	 */
	Uin("Uin", ".uin", "application/x-icq", new String[]{}),

	/**
	 * Uls mime：.uls=text/iuls
	 */
	Uls("Uls", ".uls", "text/iuls", new String[]{}),

	/**
	 * Vcf mime：.vcf=text/x-vcard
	 */
	Vcf("Vcf", ".vcf", "text/x-vcard", new String[]{}),

	/**
	 * Vda mime：.vda=application/x-vda
	 */
	Vda("Vda", ".vda", "application/x-vda", new String[]{}),

	/**
	 * Vdx mime：.vdx=application/vnd.visio
	 */
	Vdx("Vdx", ".vdx", "application/vnd.visio", new String[]{}),

	/**
	 * Vml mime：.vml=text/xml
	 */
	Vml("Vml", ".vml", "text/xml", new String[]{}),

	/**
	 * Vpg mime：.vpg=application/x-vpeg005
	 */
	Vpg("Vpg", ".vpg", "application/x-vpeg005", new String[]{}),

	/**
	 * Vsd mime：.vsd=application/vnd.visio， .vsd=application/x-tif
	 */
	Vsd("Vsd", ".vsd", "application/vnd.visio", new String[]
			{
					"application/x-vsd",
			}),

	/**
	 * Vss mime：.vss=application/vnd.visio
	 */
	Vss("Vss", ".vss", "application/vnd.visio", new String[]{}),

	/**
	 * Vst mime：.vst=application/vnd.visio，.vst=application/x-vst
	 */
	Vst("Vst", ".vst", "application/vnd.visio", new String[]
			{
					"application/x-vst",
			}),

	/**
	 * Vsw mime：.vsw=application/vnd.visio
	 */
	Vsw("Vsw", ".vsw", "application/vnd.visio", new String[]{}),

	/**
	 * Vsx mime：.vsx=application/vnd.visio
	 */
	Vsx("Vsx", ".vsx", "application/vnd.visio", new String[]{}),

	/**
	 * Vtx mime：.vtx=application/vnd.visio
	 */
	Vtx("Vtx", ".vtx", "application/vnd.visio", new String[]{}),

	/**
	 * Vxml mime：.vxml=text/xml
	 */
	Vxml("Vxml", ".vxml", "text/xml", new String[]{}),

	/**
	 * Wav mime：.wav=audio/wav
	 */
	Wav("Wav", ".wav", "audio/wav", new String[]{}),

	/**
	 * Wax mime：.wax=audio/x-ms-wax
	 */
	Wax("Wax", ".wax", "audio/x-ms-wax", new String[]{}),

	/**
	 * Wb1 mime：.wb1=application/x-wb1
	 */
	Wb1("Wb1", ".wb1", "application/x-wb1", new String[]{}),

	/**
	 * Wb2 mime：.wb2=application/x-wb2
	 */
	Wb2("Wb2", ".wb2", "application/x-wb2", new String[]{}),

	/**
	 * Wb3 mime：.wb3=application/x-wb3
	 */
	Wb3("Wb3", ".wb3", "application/x-wb3", new String[]{}),

	/**
	 * Wbmp mime：.wbmp=image/vnd.wap.wbmp
	 */
	Wbmp("Wbmp", ".wbmp", "image/vnd.wap.wbmp", new String[]{}),

	/**
	 * Wiz mime：.wiz=application/msword
	 */
	Wiz("Wiz", ".wiz", "application/msword", new String[]{}),

	/**
	 * Wk3 mime：.wk3=application/x-wk3
	 */
	Wk3("Wk3", ".wk3", "application/x-wk3", new String[]{}),

	/**
	 * Wk4 mime：.wk4=application/x-wk4
	 */
	Wk4("Wk4", ".wk4", "application/x-wk4", new String[]{}),

	/**
	 * Wkq mime：.wkq=application/x-wkq
	 */
	Wkq("Wkq", ".wkq", "application/x-wkq", new String[]{}),

	/**
	 * Wks mime：.wks=application/x-wks
	 */
	Wks("Wks", ".wks", "application/x-wks", new String[]{}),

	/**
	 * Wm mime：.wm=video/x-ms-wm
	 */
	Wm("Wm", ".wm", "video/x-ms-wm", new String[]{}),

	/**
	 * Wma mime：.wma=audio/x-ms-wma
	 */
	Wma("Wma", ".wma", "audio/x-ms-wma", new String[]{}),

	/**
	 * Wmd mime：.wmd=application/x-ms-wmd
	 */
	Wmd("Wmd", ".wmd", "application/x-ms-wmd", new String[]{}),

	/**
	 * Wmf mime：.wmf=application/x-wmf
	 */
	Wmf("Wmf", ".wmf", "application/x-wmf", new String[]{}),

	/**
	 * Wml mime：.wml=text/vnd.wap.wml
	 */
	Wml("Wml", ".wml", "text/vnd.wap.wml", new String[]{}),

	/**
	 * Wmv mime：.wmv=video/x-ms-wmv
	 */
	Wmv("Wmv", ".wmv", "video/x-ms-wmv", new String[]{}),

	/**
	 * Wmx mime：.wmx=video/x-ms-wmx
	 */
	Wmx("Wmx", ".wmx", "video/x-ms-wmx", new String[]{}),

	/**
	 * Wmz mime：.wmz=application/x-ms-wmz
	 */
	Wmz("Wmz", ".wmz", "application/x-ms-wmz", new String[]{}),

	/**
	 * Wp6 mime：.wp6=application/x-wp6
	 */
	Wp6("Wp6", ".wp6", "application/x-wp6", new String[]{}),

	/**
	 * Wpd mime：.wpd=application/x-wpd
	 */
	Wpd("Wpd", ".wpd", "application/x-wpd", new String[]{}),

	/**
	 * Wpg mime：.wpg=application/x-wpg
	 */
	Wpg("Wpg", ".wpg", "application/x-wpg", new String[]{}),

	/**
	 * Wpl mime：.wpl=application/-wpl
	 */
	Wpl("Wpl", ".wpl", "application/-wpl", new String[]{}),

	/**
	 * Wq1 mime：.wq1=application/x-wq1
	 */
	Wq1("Wq1", ".wq1", "application/x-wq1", new String[]{}),

	/**
	 * Wr1 mime：.wr1=application/x-wr1
	 */
	Wr1("Wr1", ".wr1", "application/x-wr1", new String[]{}),

	/**
	 * Wri mime：.wri=application/x-wri
	 */
	Wri("Wri", ".wri", "application/x-wri", new String[]{}),

	/**
	 * Wrk mime：.wrk=application/x-wrk
	 */
	Wrk("Wrk", ".wrk", "application/x-wrk", new String[]{}),

	/**
	 * Ws mime：.ws=application/x-ws
	 */
	Ws("Ws", ".ws", "application/x-ws", new String[]{}),

	/**
	 * Ws2 mime：.ws2=application/x-ws
	 */
	Ws2("Ws2", ".ws2", "application/x-ws", new String[]{}),

	/**
	 * Wsc mime：.wsc=text/scriptlet
	 */
	Wsc("Wsc", ".wsc", "text/scriptlet", new String[]{}),

	/**
	 * Wsdl mime：.wsdl=text/xml
	 */
	Wsdl("Wsdl", ".wsdl", "text/xml", new String[]{}),

	/**
	 * Wvx mime：.wvx=video/x-ms-wvx
	 */
	Wvx("Wvx", ".wvx", "video/x-ms-wvx", new String[]{}),

	/**
	 * Xdp mime：.xdp=application/vnd.adobe.xdp
	 */
	Xdp("Xdp", ".xdp", "application/vnd.adobe.xdp", new String[]{}),

	/**
	 * Xdr mime：.xdr=text/xml
	 */
	Xdr("Xdr", ".xdr", "text/xml", new String[]{}),

	/**
	 * Xfd mime：.xfd=application/vnd.adobe.xfd
	 */
	Xfd("Xfd", ".xfd", "application/vnd.adobe.xfd", new String[]{}),

	/**
	 * Xfdf mime：.xfdf=application/vnd.adobe.xfdf
	 */
	Xfdf("Xfdf", ".xfdf", "application/vnd.adobe.xfdf", new String[]{}),

	/**
	 * Xhtml mime：.xhtml=text/html
	 */
	Xhtml("Xhtml", ".xhtml", "text/html", new String[]{}),

	/**
	 * Xls mime：.xls=application/-excel，.xls=application/x-xls
	 */
	Xls("Xls", ".xls", "application/-excel", new String[]
			{
					"application/x-xls",
			}),

	/**
	 * Xlw mime：.xlw=application/x-xlw
	 */
	Xlw("Xlw", ".xlw", "application/x-xlw", new String[]{}),

	/**
	 * Xml mime：.xml=text/xml
	 */
	Xml("Xml", ".xml", "text/xml", new String[]{}),

	/**
	 * Xpl mime：.xpl=audio/scpls
	 */
	Xpl("Xpl", ".xpl", "audio/scpls", new String[]{}),

	/**
	 * Xq mime：.xq=text/xml
	 */
	Xq("Xq", ".xq", "text/xml", new String[]{}),

	/**
	 * Xql mime：.xql=text/xml
	 */
	Xql("Xql", ".xql", "text/xml", new String[]{}),

	/**
	 * Xquery mime：.xquery=text/xml
	 */
	Xquery("Xquery", ".xquery", "text/xml", new String[]{}),

	/**
	 * Xsd mime：.xsd=text/xml
	 */
	Xsd("Xsd", ".xsd", "text/xml", new String[]{}),

	/**
	 * Xsl mime：.xsl=text/xml
	 */
	Xsl("Xsl", ".xsl", "text/xml", new String[]{}),

	/**
	 * Xslt mime：.xslt=text/xml
	 */
	Xslt("Xslt", ".xslt", "text/xml", new String[]{}),

	/**
	 * Xwd mime：.xwd=application/x-xwd
	 */
	Xwd("Xwd", ".xwd", "application/x-xwd", new String[]{}),

	/**
	 * X_b mime：.x_b=application/x-x_b
	 */
	Xb("Xb", ".x_b", "application/x-x_b", new String[]{}),

	/**
	 * X_t mime：.x_t=application/x-x_t
	 */
	Xt("Xt", ".x_t", "application/x-x_t", new String[]{}),

	/**
	 * dotx mime：.x_b=application/vnd.openxmlformats-officedocument.wordprocessingml.template
	 */
	Dotx("Dotx", ".dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template", new String[]{}),

	/**
	 * dotx mime：.x_b=application/vnd.openxmlformats-officedocument.presentationml.presentation
	 */
	Pptx("Pptx", ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation", new String[]{}),

	/**
	 * ppsx mime：.x_b=application/vnd.openxmlformats-officedocument.presentationml.slideshow
	 */
	Ppsx("Ppsx", ".ppsx", "application/vnd.openxmlformats-officedocument.presentationml.slideshow", new String[]{}),

	/**
	 * potx mime：.x_b=application/vnd.openxmlformats-officedocument.presentationml.template
	 */
	Potx("Potx", ".potx", "application/vnd.openxmlformats-officedocument.presentationml.template", new String[]{}),

	/**
	 * xlsx mime：.x_b=application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
	 */
	Xlsx("Xlsx", ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new String[]{}),

	/**
	 * xltx mime：.x_b=application/vnd.openxmlformats-officedocument.spreadsheetml.template
	 */
	Xltx("Xltx ", ".xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template", new String[]{}),

	/**
	 * MsExcel mime：.x_b=application/vnd.ms-excel
	 */
	MsExcel("MsExcel", ".xls", "application/vnd.ms-excel", new String[]{});

	/**
	 * 名称
	 */
	private final String name;

	/**
	 * 扩展名
	 */
	private final String extension;

	/**
	 * 内容类型
	 */
	private final String contentType;

	/**
	 * 内容类型别名
	 */
	private final String[] alias;

	Mime(String name, String extension, String contentType, String[] alias) {
		this.name = name;
		this.extension = extension;
		this.contentType = contentType;
		this.alias = alias;
	}

	public String getName() {
		return name;
	}

	public String getExtension() {
		return extension;
	}

	public String getContentType() {
		return contentType;
	}

	public String[] getAlias() {
		return alias;
	}
}
