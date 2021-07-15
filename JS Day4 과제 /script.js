function sendit(){

    const userid = document.getElementById('userid');
    const userpw = document.getElementById('userpw'); // 전역 객체
    const userpw_re = document.getElementById('userpw_re'); // 전역 객체
    const name = document.getElementById('name');
    const hp = document.getElementById('hp');
    const email = document.getElementById('email');
    const hobby = document.getElementsByName('hobby');
    const ssn1 = document.getElementById('ssn1');
    const ssn2 = document.getElementById('ssn2');
    const isssn = document.getElementById('isssn');


    // 정규표현식
    // 범위를 줄때는 대괄호[] 사용
    const expUserpwText = /[A-Za-z0-9!@#$%^*+=-]$/;
    const expNameText = /[가-힣]+$/; // 1자 이상, 패턴의 마침은 $, 한글 가부터 힣까지
    // 글자수가 정해져 있을때는 $앞에 +를 하면 안된다.
    const expHpText = /^010\d{3,4}\d{4}$/; // ^은 무조건 시작해야한다. \d: 정수 , {}: 글자 갯수 -: 하이픈
    const expEmailText = /^[A-Za-z0-9\.\-]+@[A-Za-z0-9\.\-]+\.[A-Za-z0-9]+$/;


    // 아이디를 입력하지 않았을 경우
    if(userid.value == ''){
        alert('아이디를 입력하세요');
        userid.focus(); // 경고창이 뜬 후 커서가 깜박임
        return false; // 더이상 진행하지 말아라
    }

    // 사용자쪽에서 한번 거르고 서버에서 거르고 마지막으로 DB에서 거르게 된다.

    // 아이디가 4자 미만 또는 16자를 초과하는 경우
    if(userid.value.length < 4 || userid.value.length > 16){
        alert('아이디를 4자 이상 16자 이하로 입력하세요');
        userid.focus();
        return false;
    }
    

    // 비밀번호를 입력하지 않은 경우
    if(userpw.value == ''){
        alert('비밀번호를 입력하세요');
        userpw.focus(); 
        return false; 
    }

    // 비밀번호 정규식
    if(!expUserpwText.test(userpw.value)){
        alert('비밀번호는 숫자, 대소문자, 특수문자를 꼭 포함해야 합니다.');
        userpw.focus();
        return false;
    }

    // 비밀번호하고 비밀번호 확인의 값이 다를 경우
    if(userpw.value != userpw_re.value){
        alert('비밀번호와 비밀번호 확인의 값이 다릅니다.');
        userpw_re.focus();
        return false;
    }
    

    // 스크립트는 차례대로 읽기 때문에 순서대로 작성하는 것이 중요!
    // 이름 입력하기
    if(!expNameText.test(name.value)){
        alert('이름 형식을 확인하세요 \n 한글만 입력 가능합니다.');
        name.focus();
        return false;
    }

    // 전화번호
    if(!expHpText.test(hp.value)){
        alert('휴대폰 번호를 확인하세요 \n 010으로 번호로 입력하세요. \n 하이픈(-) 없이 입력하세요.');
        hp.focus();
        return false;
    }

    // 이메일
    if(!expEmailText.test(email.value)){
        alert('이메일 형식을 확인하세요');
        email.focus();
        return false;
    }

    // 취미
    let count = 0;
    for(let i in hobby){
        if(hobby[i].checked){
            count++;
        }
    }

    if(count == 0){
        alert('취미는 1개이상 선택하세요');
        return false;
    }

    // 주민등록번호 검증을 눌러서 y로 바뀌면 가입완료가 되도록 설정
    if(isssn.value == 'n'){
        alert('주민등록번호 검증버튼을 눌러주세요!');
        return false;
    }

    return true;
}

function ssnCheck(){
    const ssn1 = document.getElementById('ssn1');
    const ssn2 = document.getElementById('ssn2');
    const isssn = document.getElementById('isssn');
    
    if(ssn1.value == ' ' || ssn2.value == ' '){
        alert('주민등록번호를 입력하세요');
        ssn1.focus();
        return false;
    }

    const ssn = ssn1.value + ssn2.value;    // 0010113068518

    const s1 = Number(ssn.substr(0, 1)) * 2;
    const s2 = Number(ssn.substr(1, 1)) * 3;
    const s3 = Number(ssn.substr(2, 1)) * 4;
    const s4 = Number(ssn.substr(3, 1)) * 5;
    const s5 = Number(ssn.substr(4, 1)) * 6;
    const s6 = Number(ssn.substr(5, 1)) * 7;
    const s7 = Number(ssn.substr(6, 1)) * 8;
    const s8 = Number(ssn.substr(7, 1)) * 9;
    const s9 = Number(ssn.substr(8, 1)) * 2;
    const s10 = Number(ssn.substr(9, 1)) * 3;
    const s11 = Number(ssn.substr(10, 1)) * 4;
    const s12 = Number(ssn.substr(11, 1)) * 5;
    const s13 = Number(ssn.substr(12, 1));
    
    let result = s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11+s12;
    result = result % 11;
    result = 11- result;
    if(result >= 10) result = result % 10;

    if(result == s13){
        alert('유효한 주민등록번호입니다');
        isssn.value = 'y'; // 주민등록번호 검증이 완료되면 y로 바꿈
    }else{
        alert('유효하지 않은 주민등록번호입니다');
    }
}

// 주민등록번호 앞에가 6자리 이상이면 커서가 뒤로 옮겨지도록 하는 코드
function moveFocus(){
    const ssn1 = document.getElementById('ssn1');
    if(ssn1.value.length >= 6){
        document.getElementById('ssn2').focus();
    }
}

// 검증을 누르고 다시 수정하면 value=n으로 바꿔서 다시 검증버튼 누르게 하는 코드
function changeSsn(){ 
    const isssn = document.getElementById('isssn');
    isssn.value = 'n';

}