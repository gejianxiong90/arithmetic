package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.cn/problems/substring-with-concatenation-of-all-words/?envType=company&envId=aliyun&favoriteSlug=aliyun-six-months
 */
public class FindSubString {

    public static void main(String[] args) {
        String s = "ejwwmybnorgshugzmoxopwuvshlcwasclobxmckcvtxfndeztdqiakfusswqsovdfwatanwxgtctyjvsmlcoxijrahivwfybbbudosawnfpmomgczirzscqvlaqhfqkithlhbodptvdhjljltckogcjsdbbktotnxgwyuapnxuwgfirbmdrvgapldsvwgqjfxggtixjhshnzphcemtzsvodygbxpriwqockyavfscvtsewyqpxlnnqnvrkmjtjbjllilinflkbfoxdhocsbpirmcbznuioevcojkdqvoraeqdlhffkwqbjsdkfxstdpxryixrdligpzldgtiqryuasxmxwgtcwsvwasngdwovxzafuixmjrobqbbnhwpdokcpfpxinlfmkfrfqrtzkhabidqszhxorzfypcjcnopzwigmbznmjnpttflsmjifknezrneedvgzfmnhoavxqksjreddpmibbodtbhzfehgluuukupjmbbvshzxyniaowdjamlfssndojyyephstlonsplrettspwepipwcjmfyvfybxiuqtkdlzqedjxxbvdsfurhedneauccrkyjfiptjfxmpxlssrkyldfriuvjranikluqtjjcoiqffdxaukagphzycvjtvwdhhxzagkevvuccxccuoccdkbboymjtimdrmerspxpktsmrwrlkvpnhqrvpdekmtpdfuxzjwpvqjjhfaupylefbvbsbhdncsshmrhxoyuejenqgjheulkxjnqkwvzznriclrbzryfaeuqkfxrbldyusoeoldpbwadhrgijeplijcvqbormrqglgmzsprtmryvkeevlthvflsvognbxfjilwkdndyzwwxgdbeqwlldyezmkopktzugxgkklimhhjqkmuaifnodtpredhqygmedtqpezboimeuyyujfjxkdmbjpizpqltvgknnlodtbhnbhjkmuhwxvzgmkhbcvvadhnssbvneecglnqxhavhvxpkjxlluilzpysjcnwguyofnhfvhaceztoiscumkhociglkvispihvyoatxcxbeqsmluixgsliatukrecgoldmzfhwkgaqzsckonjuhxdhqztjfxstjvikdrhpyjfxbjjryslfpqoiphrwfjqqhaamrjbrsiovrxmqsyxhqmritjeauwqbwtpqcqhvyyssvfknfhxvtodpzipueixdbntdfcaeatyyainfpkclbgaaqrwwzwbcjwiqzkwzfuxfclmsxpdyvfbnwxjytnaejivivriamhgqsskqhnqeurttrfrmstrbeokzhuzvbfmwywohmgogyhzpmsdemugqkspsmoppwbnwabdmiruibwznqcuczculujfiavzwynsyqxmarjkshjhxobandwyzggjibjgzyaaqxorqxbkenscbveqbaociwmqxxyzvyblypeongzrttvwqzmrccwkzidyfbxcaypyquodcpwxkstbthuvjqgialhfmgjohzoxvdaxuywfqrgmyahhtpqtazbphmfoluliznftodyguesshcacrsvutylalqrykehjuofisdookjhrljvedsywrlyccpaowjaqyfaqioesxnlkwgpbznzszyudpwrlgrdgwdyhucztsneqttsuirmjriohhgunzatyfrfzvgvptbgpwajgtysligupoqeoqxoyqtzozufvvlktnvahvsseymtpeyfvxttqosgpplkmxwgmsgtpantazppgnubmpwcdqkvhwfuvcahwibniohiqyywnuzzmxeppokxksrfwrpuzqhjgqryorwboxdauhrkxehiwaputeouwxdfoudcoagcxjcuqvenznxxnprgvhasffxtzaxpcfrcovwgrcwqptoekhmgpoywtxruxokcubekzcrqengviwbtgnzvdzrwwkqvacxwgdhffyvjldgvchoiwnfzoyvkiogisdfyjmfomcazigukqlumyzmnzjzhzfpslwsukykwckvktswjdqxdrlsqvsxwxpqkljeyjpulbswwmuhplfueqnvnhukgjarxlxvwmriqjgmxawmndhsvwnjdjvjtxcsjapfogpesxtpypenunfpjuyoevzztctecilqqbxkaqcyhiobvtqgqruumvvhxolbyzsqcrdchhdqprtkkjsccowrjtyjjmkhleanvfpemuublnnyzfabtxsestncfalqenfcswgerbfcqsapzdtscnzugmwlmidtxkvqhbuaecevwhmwkfqmvpgbefpqpsjmdecmixmmbsjxzwvjdmxydechlraajjmoqpcyoqmrjwoiumuzatydzcnktnkeyztoqvogodxxznhvzduzxudwwqhpftwdspuimioanlzobhjakgajafgzxpqckmhdbbnqmcszpuoqbztnftzgahhxwxbgkilnmzfydyxusnnvngksbjabqjaohdvrniezhmxmkxhemwbbclwdxwgngicplzgajmaryzfkyoqlkrmmfirchzrphveuwmvgaxzbwenvteifxuuefnimnadwxhruvoavlzyhfmeasmgrjawongccgfbgoualiaivbhcgvjjnxpggrewglalthmzvgziobrjeanlvyukwlscexbkibvdjhdgnepdiimmkcxhattwglbkicvsfswocbvphmtpwhcgjbnmxgidtlqcnnwtfujhvgzdussqbwynylzvtjapvqtidpdjkpshvrmqlhindhabubyokzdfrwqvnvgzkyhistydagsgnujiviyijdnabfxqbdqnexvwsvzvcsbrmkbkuzsdehghndyqjodnnblfwmaygdstotfkvxozgwhtbhlkvrzismnozqpfthajafuxekzlgigjpsukjvsdihrjzgovnreqwapdkoqswyclqyvbvpedzyoyedvuuamscbxnqnfmmjyehvidnoimmxmtcinwkbqmcobubjjpshucechrqrffqsyscnqoohcsxenypyqhfklloudgmklcejvgynwouzhtfwuuukdbwpmkjrqxeeaipxrokncholathupdetgaktmvmftqjvzyssocftjwemroghrncynmtchhhcaqxbqpthuaafwgrouaxonzocljeuslzsdwvuoodipdpnlhdihaywzmymxdjrqikughquwtenyucjdgrmipiidiwclhuepgyynoslhzahtdqwliktzsddaahohbszhqxxgripqlwlomjbwtuynydoakejmwkvojuwbfltqjfgxqhwkduzbxpdhtpvrzrfjndmsqfizmqxdxtpbpoemekvxzrrakwjxcxqsdasptruqmjtbaapgmkfnbwnlvzlxwdpzfjryanrmzmpzoefapmnsjdgecrdywsabctaegttffigupnwgakylngrrxurtotxqmzxvsqazajvrwsxyeyjteakeudzjxwbjvagnsjntskmocmpgkybqbnwvrwgoskzqkgffpsyhfmxhymqinrbohxlytsmoeleqrjvievpjipsgdkrqeuglrsjnmvdsihicsgkybcjltcswolpsfxdypmlbjotuxewskisnmczfgreuevnjssjifvlqlhkllifxrxkdbjlhcpegmtrelbosyajljvwwedtxbdccpnmreqaqjrxwulpunagwxesbilalrdniqbzxrbpcvmzpyqklsskpwctgqtrjwhrpisocwderqfiqxsdpkphjsapkvhvsqojyixaechvuoemmyqdlfkuzmlliugckuljfkljoshjhlvvlnywvjswvekfyqhjnsusefdtakejxbejrchoncklguqgnyrcslwztbstmycjziuskegagtlonducdogwbevugppsptdqbajmepmmizaycwcgmjeopbivsyphtvxvvgjbyxpgwpganjiaumojpyhhywosrmnouwpstgbrvhtlqcnmqbygbfnabesvshjmdbhyhirfrkqkmfwdgujhzyjdcbyuijjnkqluaczrnrbbwaeeupnwqzbsazplkyaxqorqsshhlljjlpphhedxdepgfgrqerpuhgmaawhnhqwsgnznrfmxjbdrkwjopylxezxgvetcvrwdewsxdeumhzfrvoilmvksuhyqltuimrnsphqslmgvmmojawwptghonigbdclqtbikiacwpjrbxhmzejozpypfixglatdvuogdoizdtsgsztsfcihtgwyqugeuahpuvvzmgarbsyuutmbxuisdfrvbxzxzhmuektssuktoknkfbmcwwubbnwenybmfqglaceuyqnoadzfenjcjfdlvcpiatuhjdujhaffqsvqvuxchgerokejovrqonxxstibunikiedfyahijobxyhimebctobsjudkqstbcxgixgrhpfiofpwruzvpqyjzvollheoldutddnksutjakhtghpxxnjykxjwgqmsvhnykclexepxqxqzghwfxfdhfmflesfabvanxlrurjtigkjotftqnwyskffpxlragrnfffawqtgyfpmzxfpkdpenxlewyxxgrkmwrmshhzfnorolyfxbvdrspxqnxnuoygkruczddgssygfymdcjgvdxutlrhffhnpyjuxmxefrelxezcgikdliyhvpocvvpkvagvmezrxffujeysplvavtjqjxsgujqsjznxforctwzecxyrkwufpdxadrgzczrnyelfschnagucguuqqqwitviynrypsrdswqxqsegulcwrwsjnihxedfcqychqumiscfkwmqqxunqrfbgqjdwmkyelbldxympctbzfupeocwhkypchuyvhybsbmvymjppfrqmlfrbkpjwpyyytytawuuyjrwxboogfessmltwdcssdqtwomymjskujjtmxiueopwacrwfuqazitvyhvlspvoaeipdsjhgyfjbxhityisidnhlksfznubucqxwaheamndjxmcxwufajmnveuwuoyosqnoqwvtjkwuhkzghvmjhawcfszbhzrbpgsidnbmxxihihnrfbamcyojqpkzodbejtmmipahojoysepzhpljpaugrghgjimtdahnpivdtlcnptnxjyiaafislqavamqgmxtdfoiaakorebqpbbpegawrqymqkewycsdjglkiwaacdqterkixkgraedtqirqmjtvsfhadhafktyrmkzmvidxmisfskvevpcnujqxrqedleuyowkjgphsxzzqlvujkwwgiodbfjesnbsbzcnftuzrvzjjudsgcqmmfpnmyrenuxotbbyvxyovzxgtcyzgqnsvcfhczoptnfnojnlinbfmylhdlijcvcxzjhdixuckaralemvsnbgooorayceuedtomzyjtctvtwgyiesxhynvogxnjdjphcftbefxgasawzagfugmuthjahylkhatlgpnkuksuesrduxkodwjzgubpsmzzmvkskzeglxaqrrvmrgcwcnvkhwzbibaxwnriowoavosminabvfxastkcrkdclgzjvqrjofjjvbyfragofeoazzeqljuypthkmywaffmcjkickqqsuhsviyovhitxeajqahshpejaqtcdkuvgdpclnsguabtgbfwdmrmbvydorfrbcokfdmtsgboidkpgpnmdeyhawkqqshtwxdbarwuxykgduxjlkxppwyruihkcqgynjcpbylayvgdqfpbqmshksyfbhrfxxemhgbkgmkhjtkzyzdqmxxwqvdtevyducpdksntgyaqtkrrkwiyuhukfadjvdnrievszilfinxbyrvknfihmetreydbcstkwoexwsfhfekfvfplmxszcosgovisnbemrjlndqwkvhqsofdbdychmupcsxvhazvrihhnxfyumonbvqeyoghccxfuwacxzxqkezxefxarnnujgyjugrzjoefmghjfhcrnbrtgouaehwnnxwkdplodpuqxdbemfwahptpfppjzowoltyqijfoabgzejerpatwponuefgdtcrgxswiddygeeflpjeelzccnsztxfyqhqyhkuppapvgvdtkmxraytcolbhkiiasaazkvqzvfxbaaxkoudovxrjkusxdazxaawmvoostlvvnsfbpjqkijvudpriqrfsrdfortimgdhtypunakzituezjyhbrpuksbamuiycngvlvpyvczfxvlwhjgicvempfobbwadkiavdswyuxdttoqaaykctprkwfmyeodowglzyjzuhencufcwdobydslazxadnftllhmjslfbrtdlahkgwlebdpdeofidldoymakfnpgekmsltcrrnxvspywfggjrmxryybdltmsfykstmlnzjitaipfoyohkmzimcozxardydxtpjgquoluzbznzqvlewtqyhryjldjoadgjlyfckzbnbootlzxhupieggntjxilcqxnocpyesnhjbauaxcvmkzusmodlyonoldequfunsbwudquaurogsiyhydswsimflrvfwruouskxjfzfynmrymyyqsvkajpnanvyepnzixyteyafnmwnbwmtojdpsucthxtopgpxgnsmnsrdhpskledapiricvdmtwaifrhnebzuttzckroywranbrvgmashxurelyrrbslxnmzyeowchwpjplrdnjlkfcoqdhheavbnhdlltjpahflwscafnnsspikuqszqpcdyfrkaabdigogatgiitadlinfyhgowjuvqlhrniuvrketfmboibttkgakohbmsvhigqztbvrsgxlnjndrqwmcdnntwofojpyrhamivfcdcotodwhvtuyyjlthbaxmrvfzxrhvzkydartfqbalxyjilepmemawjfxhzecyqcdswxxmaaxxyifmouauibstgpcfwgfmjlfhketkeshfcorqirmssfnbuqiqwqfhbmol";
        String[] words = {"toiscumkhociglkvispihvyoatxcx", "ndojyyephstlonsplrettspwepipw", "yzfkyoqlkrmmfirchzrphveuwmvga", "mxxihihnrfbamcyojqpkzodbejtmm", "fenjcjfdlvcpiatuhjdujhaffqsvq", "ehghndyqjodnnblfwmaygdstotfkv", "heoldutddnksutjakhtghpxxnjykx", "cvrwdewsxdeumhzfrvoilmvksuhyq", "ftqjvzyssocftjwemroghrncynmtc", "idiwclhuepgyynoslhzahtdqwlikt", "eurttrfrmstrbeokzhuzvbfmwywoh", "jxlluilzpysjcnwguyofnhfvhacez", "uskegagtlonducdogwbevugppsptd", "xmcxwufajmnveuwuoyosqnoqwvtjk", "wolpsfxdypmlbjotuxewskisnmczf", "fjryanrmzmpzoefapmnsjdgecrdyw", "jgmxawmndhsvwnjdjvjtxcsjapfog", "wuhkzghvmjhawcfszbhzrbpgsidnb", "yelbldxympctbzfupeocwhkypchuy", "vzduzxudwwqhpftwdspuimioanlzo", "bdpdeofidldoymakfnpgekmsltcrr", "fmyeodowglzyjzuhencufcwdobyds", "dhtypunakzituezjyhbrpuksbamui", "bdmiruibwznqcuczculujfiavzwyn", "eudzjxwbjvagnsjntskmocmpgkybq", "tuynydoakejmwkvojuwbfltqjfgxq", "psrdswqxqsegulcwrwsjnihxedfcq", "cokfdmtsgboidkpgpnmdeyhawkqqs", "fujhvgzdussqbwynylzvtjapvqtid", "rqeuglrsjnmvdsihicsgkybcjltcs", "vhybsbmvymjppfrqmlfrbkpjwpyyy", "aukagphzycvjtvwdhhxzagkevvucc", "hwkduzbxpdhtpvrzrfjndmsqfizmq", "ywnuzzmxeppokxksrfwrpuzqhjgqr", "qbajmepmmizaycwcgmjeopbivsyph", "uamscbxnqnfmmjyehvidnoimmxmtc", "nxvspywfggjrmxryybdltmsfykstm", "amrjbrsiovrxmqsyxhqmritjeauwq", "yorwboxdauhrkxehiwaputeouwxdf", "qkewycsdjglkiwaacdqterkixkgra", "ycngvlvpyvczfxvlwhjgicvempfob", "jgphsxzzqlvujkwwgiodbfjesnbsb", "mkxhemwbbclwdxwgngicplzgajmar", "mryvkeevlthvflsvognbxfjilwkdn", "mezrxffujeysplvavtjqjxsgujqsj", "rtotxqmzxvsqazajvrwsxyeyjteak", "sabctaegttffigupnwgakylngrrxu", "xccuoccdkbboymjtimdrmerspxpkt", "xusnnvngksbjabqjaohdvrniezhmx", "oyuejenqgjheulkxjnqkwvzznricl", "mxszcosgovisnbemrjlndqwkvhqso", "wsgnznrfmxjbdrkwjopylxezxgvet", "dxmisfskvevpcnujqxrqedleuyowk", "dhrgijeplijcvqbormrqglgmzsprt", "vuxchgerokejovrqonxxstibuniki", "lumyzmnzjzhzfpslwsukykwckvkts", "inwkbqmcobubjjpshucechrqrffqs", "ywtxruxokcubekzcrqengviwbtgnz", "ccpnmreqaqjrxwulpunagwxesbila", "pesxtpypenunfpjuyoevzztctecil", "sygfymdcjgvdxutlrhffhnpyjuxmx", "uisdfrvbxzxzhmuektssuktoknkfb", "cejvgynwouzhtfwuuukdbwpmkjrqx", "oudcoagcxjcuqvenznxxnprgvhasf", "sxnlkwgpbznzszyudpwrlgrdgwdyh", "qqbxkaqcyhiobvtqgqruumvvhxolb", "mkhleanvfpemuublnnyzfabtxsest", "bibaxwnriowoavosminabvfxastkc", "bcxgixgrhpfiofpwruzvpqyjzvoll", "lzccnsztxfyqhqyhkuppapvgvdtkm", "pdjkpshvrmqlhindhabubyokzdfrw", "qbbnhwpdokcpfpxinlfmkfrfqrtzk", "rnyelfschnagucguuqqqwitviynry", "qtrjwhrpisocwderqfiqxsdpkphjs", "vxttqosgpplkmxwgmsgtpantazppg", "tyisidnhlksfznubucqxwaheamndj", "kgaqzsckonjuhxdhqztjfxstjvikd", "jeuslzsdwvuoodipdpnlhdihaywzm", "vdzrwwkqvacxwgdhffyvjldgvchoi", "cftbefxgasawzagfugmuthjahylkh", "xraytcolbhkiiasaazkvqzvfxbaax", "oyqtzozufvvlktnvahvsseymtpeyf", "rnnujgyjugrzjoefmghjfhcrnbrtg", "rfzvgvptbgpwajgtysligupoqeoqx", "igbdclqtbikiacwpjrbxhmzejozpy", "dyzwwxgdbeqwlldyezmkopktzugxg", "hmetreydbcstkwoexwsfhfekfvfpl", "zcnftuzrvzjjudsgcqmmfpnmyrenu", "zzmvkskzeglxaqrrvmrgcwcnvkhwz", "vjswvekfyqhjnsusefdtakejxbejr", "rwwzwbcjwiqzkwzfuxfclmsxpdyvf", "fdbdychmupcsxvhazvrihhnxfyumo", "vdtevyducpdksntgyaqtkrrkwiyuh", "nbvqeyoghccxfuwacxzxqkezxefxa", "vpgbefpqpsjmdecmixmmbsjxzwvjd", "jwgqmsvhnykclexepxqxqzghwfxfd", "olyfxbvdrspxqnxnuoygkruczddgs", "qgmxtdfoiaakorebqpbbpegawrqym", "liaivbhcgvjjnxpggrewglalthmzv", "choncklguqgnyrcslwztbstmycjzi", "fpkdpenxlewyxxgrkmwrmshhzfnor", "hhhcaqxbqpthuaafwgrouaxonzocl", "ipahojoysepzhpljpaugrghgjimtd", "wosrmnouwpstgbrvhtlqcnmqbygbf", "nwyskffpxlragrnfffawqtgyfpmzx", "bcvvadhnssbvneecglnqxhavhvxpk", "hoavxqksjreddpmibbodtbhzfehgl", "lazxadnftllhmjslfbrtdlahkgwle", "uuukupjmbbvshzxyniaowdjamlfss", "tpqtazbphmfoluliznftodyguessh", "ychqumiscfkwmqqxunqrfbgqjdwmk", "rkdclgzjvqrjofjjvbyfragofeoaz", "pphhedxdepgfgrqerpuhgmaawhnhq", "cacrsvutylalqrykehjuofisdookj", "kyldfriuvjranikluqtjjcoiqffdx", "bnwvrwgoskzqkgffpsyhfmxhymqin", "uzmlliugckuljfkljoshjhlvvlnyw", "abfxqbdqnexvwsvzvcsbrmkbkuzsd", "xotbbyvxyovzxgtcyzgqnsvcfhczo", "bwtpqcqhvyyssvfknfhxvtodpzipu", "nsfbpjqkijvudpriqrfsrdfortimg", "tgwyqugeuahpuvvzmgarbsyuutmbx", "upnwqzbsazplkyaxqorqsshhlljjl", "edfyahijobxyhimebctobsjudkqst", "ialhfmgjohzoxvdaxuywfqrgmyahh", "jlhcpegmtrelbosyajljvwwedtxbd", "tpfppjzowoltyqijfoabgzejerpat", "mgogyhzpmsdemugqkspsmoppwbnwa", "nubmpwcdqkvhwfuvcahwibniohiqy", "ukfadjvdnrievszilfinxbyrvknfi", "dgnepdiimmkcxhattwglbkicvsfsw", "syqxmarjkshjhxobandwyzggjibjg", "bnwxjytnaejivivriamhgqsskqhnq", "hzyjdcbyuijjnkqluaczrnrbbwaee", "yscnqoohcsxenypyqhfklloudgmkl", "habidqszhxorzfypcjcnopzwigmbz", "wjdqxdrlsqvsxwxpqkljeyjpulbsw", "tytawuuyjrwxboogfessmltwdcssd", "pfixglatdvuogdoizdtsgsztsfcih", "apkvhvsqojyixaechvuoemmyqdlfk", "ouaehwnnxwkdplodpuqxdbemfwahp", "ixuckaralemvsnbgooorayceuedto", "ymxdjrqikughquwtenyucjdgrmipi", "smrwrlkvpnhqrvpdekmtpdfuxzjwp", "bhjakgajafgzxpqckmhdbbnqmcszp", "beqsmluixgsliatukrecgoldmzfhw", "greuevnjssjifvlqlhkllifxrxkdb", "yzsqcrdchhdqprtkkjsccowrjtyjj", "sviyovhitxeajqahshpejaqtcdkuv", "qtwomymjskujjtmxiueopwacrwfuq", "mzyjtctvtwgyiesxhynvogxnjdjph", "dyfbxcaypyquodcpwxkstbthuvjqg", "hfmflesfabvanxlrurjtigkjotftq", "mxydechlraajjmoqpcyoqmrjwoium", "nabesvshjmdbhyhirfrkqkmfwdguj", "bhrfxxemhgbkgmkhjtkzyzdqmxxwq", "gziobrjeanlvyukwlscexbkibvdjh", "mcwwubbnwenybmfqglaceuyqnoadz", "xyzvyblypeongzrttvwqzmrccwkzi", "ncfalqenfcswgerbfcqsapzdtscnz", "dtqpezboimeuyyujfjxkdmbjpizpq", "wmuhplfueqnvnhukgjarxlxvwmriq", "qwapdkoqswyclqyvbvpedzyoyedvu", "uoqbztnftzgahhxwxbgkilnmzfydy", "zsddaahohbszhqxxgripqlwlomjbw", "bwadkiavdswyuxdttoqaaykctprkw", "eixdbntdfcaeatyyainfpkclbgaaq", "nmjnpttflsmjifknezrneedvgzfmn", "avlzyhfmeasmgrjawongccgfbgoua", "kklimhhjqkmuaifnodtpredhqygme", "xzbwenvteifxuuefnimnadwxhruvo", "ugmwlmidtxkvqhbuaecevwhmwkfqm", "rhpyjfxbjjryslfpqoiphrwfjqqha", "eeaipxrokncholathupdetgaktmvm", "ltuimrnsphqslmgvmmojawwptghon", "azitvyhvlspvoaeipdsjhgyfjbxhi", "efrelxezcgikdliyhvpocvvpkvagv", "znxforctwzecxyrkwufpdxadrgzcz", "kcqgynjcpbylayvgdqfpbqmshksyf", "hrljvedsywrlyccpaowjaqyfaqioe", "cjmfyvfybxiuqtkdlzqedjxxbvdsf", "zeqljuypthkmywaffmcjkickqqsuh", "wnfzoyvkiogisdfyjmfomcazigukq", "zyaaqxorqxbkenscbveqbaociwmqx", "ahnpivdtlcnptnxjyiaafislqavam", "edtqirqmjtvsfhadhafktyrmkzmvi", "wponuefgdtcrgxswiddygeeflpjee", "xozgwhtbhlkvrzismnozqpfthajaf", "ptnfnojnlinbfmylhdlijcvcxzjhd", "uxekzlgigjpsukjvsdihrjzgovnre", "rbohxlytsmoeleqrjvievpjipsgdk", "fxtzaxpcfrcovwgrcwqptoekhmgpo", "tvxvvgjbyxpgwpganjiaumojpyhhy", "vqjjhfaupylefbvbsbhdncsshmrhx", "urhedneauccrkyjfiptjfxmpxlssr", "ltvgknnlodtbhnbhjkmuhwxvzgmkh", "ucztsneqttsuirmjriohhgunzatyf", "rbzryfaeuqkfxrbldyusoeoldpbwa", "atlgpnkuksuesrduxkodwjzgubpsm", "lrdniqbzxrbpcvmzpyqklsskpwctg", "qvnvgzkyhistydagsgnujiviyijdn", "uzatydzcnktnkeyztoqvogodxxznh", "ocbvphmtpwhcgjbnmxgidtlqcnnwt", "koudovxrjkusxdazxaawmvoostlvv", "ptruqmjtbaapgmkfnbwnlvzlxwdpz", "xdxtpbpoemekvxzrrakwjxcxqsdas", "gdpclnsguabtgbfwdmrmbvydorfrb", "htwxdbarwuxykgduxjlkxppwyruih"};
        List<Integer> substring = findSubstring3(s, words);

        substring.forEach(ss -> System.out.println(ss));
    }

    public static List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //将所有移动分成 wordLen 类情况
        for (int j = 0; j < wordLen; j++) {
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0; //记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
            //每次移动一个单词长度
            for (int i = j; i < s.length() - wordNum * wordLen + 1; i = i + wordLen) {
                boolean hasRemoved = false; //防止情况三移除后，情况一继续移除
                while (num < wordNum) {
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        //出现情况三，遇到了符合的单词，但是次数超了
                        if (hasWords.get(word) > allWords.get(word)) {
                            // hasWords.put(word, value);
                            hasRemoved = true;
                            int removeNum = 0;
                            //一直移除单词，直到次数符合了
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1; //加 1 是因为我们把当前单词加入到了 HashMap 2 中
                            i = i + (removeNum - 1) * wordLen; //这里依旧是考虑到了最外层的 for 循环，看情况二的解释
                            break;
                        }
                        //出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                        //只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                        //然后刚好就移动到了单词后边）
                    } else {
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == wordNum) {
                    res.add(i);

                }
                //出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num = num - 1;
                }

            }

        }
        return res;
    }


    public static List<Integer> findSubstring2(String s, String[] words) {
        int n = words.length;
        int m = words[0].length();
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            Integer val = wordMap.getOrDefault(word, 0);
            wordMap.put(word, val + 1);
        }
        for (int j = 0; j < m; j++) {
            int num = 0;
            HashMap<String, Integer> hasWord = new HashMap<>();
            for (int i = j; i < s.length() - n * m + 1; i += m) {
                boolean hasRemoved = false;
                while (num < n) {
                    String word = s.substring(i + num * m, i + num * m + m);
                    if (wordMap.containsKey(word)) {
                        Integer value = hasWord.getOrDefault(word, 0);
                        hasWord.put(word, value + 1);
                        if (hasWord.get(word) > wordMap.get(word)) {
                            int removeNum = 0;
                            hasRemoved = true;
                            while (hasWord.get(word) > wordMap.get(word)) {
                                String removeSub = s.substring(i + removeNum * m, i + removeNum * m + m);
                                Integer v = hasWord.get(removeSub);
                                hasWord.put(removeSub, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1;
                            i = i + (removeNum - 1) * m;
                            break;
                        }

                    } else {
                        hasWord.clear();
                        i = i + num * m;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == n) {
                    res.add(i);
                }
                if (num > 0 && !hasRemoved) {
                    String firstword = s.substring(i, i + m);
                    Integer v = hasWord.get(firstword);
                    hasWord.put(firstword, v - 1);
                    num--;
                }

            }
        }

        return res;
    }


    public static List<Integer> findSubstring(String s, String[] words) {
        int n = words.length;
        int m = words[0].length();
        HashMap<String, Integer> wordMap = new HashMap<>();
        List<Integer> res = new ArrayList<Integer>();
        for (String word : words) {
            int value = wordMap.getOrDefault(word, 0);
            wordMap.put(word, value + 1);
        }

        for (int i = 0; i < s.length() - n * m + 1; i++) {
            HashMap<String, Integer> hasWord = new HashMap<>();
            int num = 0;

            while (num < n) {
                String sub = s.substring(i + num * m, i + num * m + m);
                if (!wordMap.containsKey(sub)) {
                    break;
                }
                int count = hasWord.getOrDefault(sub, 0);
                hasWord.put(sub, count + 1);
                if (hasWord.get(sub) > wordMap.get(sub)) {
                    break;
                }
                num++;
            }
            if (num == n) {
                res.add(i);
            }
        }
        return res;
    }


    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        //HashMap1 存所有单词
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //遍历所有子串
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            //HashMap2 存当前扫描的字符串含有的单词
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0;
            //判断该子串是否符合
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                //判断该单词在 HashMap1 中
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    //判断当前单词的 value 和 HashMap1 中该单词的 value
                    if (hasWords.get(word) > allWords.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }
            //判断是不是所有的单词都符合条件
            if (num == wordNum) {
                res.add(i);
            }
        }
        return res;
    }


}
