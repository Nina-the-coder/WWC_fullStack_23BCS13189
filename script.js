function x(){
    for(let i = 1; i<=3; i++){
        setTimeout(()=>{
            console.log(i);
        }, i*2000);
    }
}
x();