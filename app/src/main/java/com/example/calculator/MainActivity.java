package com.example.calculator;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.TextView;
//代码文件
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button but_DEL,but_num0,but_num1,but_num2,but_num3,but_num4,but_num5,but_num6,but_num7,but_num8,but_num9,but_ADD,but_MINUS,but_MULT,but_DIV,but_POINT,but_EQUAL,but_AC;

    TextView textview;
    boolean clear_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//设置布局文件
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViewAndListener();
    }
//初始化每一个按钮，并且对按钮设置监听
    private void initViewAndListener() {
        but_num0=(Button)findViewById(R.id.bt_NUM0);
        but_num0.setOnClickListener(this);
        but_num1=(Button)findViewById(R.id.bt_NUM1);
        but_num1.setOnClickListener(this);
        but_num2=(Button)findViewById(R.id.bt_NUM2);
        but_num2.setOnClickListener(this);
        but_num3=(Button)findViewById(R.id.bt_NUM3);
        but_num3.setOnClickListener(this);
        but_num4=(Button)findViewById(R.id.bt_NUM4);
        but_num4.setOnClickListener(this);
        but_num5=(Button)findViewById(R.id.bt_NUM5);
        but_num5.setOnClickListener(this);
        but_num6=(Button)findViewById(R.id.bt_NUM6);
        but_num6.setOnClickListener(this);
        but_num7=(Button)findViewById(R.id.bt_NUM7);
        but_num7.setOnClickListener(this);
        but_num8=(Button)findViewById(R.id.bt_NUM8);
        but_num8.setOnClickListener(this);
        but_num9=(Button)findViewById(R.id.bt_NUM9);
        but_num9.setOnClickListener(this);
        but_ADD=(Button)findViewById(R.id.bt_ADD);
        but_ADD.setOnClickListener(this);
        but_MINUS=(Button)findViewById(R.id.bt_MINUS);
        but_MINUS.setOnClickListener(this);
        but_MULT=(Button)findViewById(R.id.bt_MULT);
        but_MULT.setOnClickListener(this);
        but_DIV=(Button)findViewById(R.id.bt_DIV);
        but_DIV.setOnClickListener(this);
        but_POINT=(Button)findViewById(R.id.bt_POINT);
        but_POINT.setOnClickListener(this);
        but_EQUAL=(Button)findViewById(R.id.bt_EQUAL);
        but_EQUAL.setOnClickListener(this);
        but_AC=(Button)findViewById(R.id.bt_AC);
        but_AC.setOnClickListener(this);
        but_DEL=(Button)findViewById(R.id.bt_DEL);
        but_DEL.setOnClickListener(this);

        textview=(TextView) findViewById(R.id.cal_monitor);
    }

//触发按钮后执行操作
    @Override
    public void onClick(View v) {
        String current=textview.getText().toString();//取出当前字符串
        switch (v.getId()){
            case R.id.bt_NUM0:
            case R.id.bt_NUM1:
            case R.id.bt_NUM2:
            case R.id.bt_NUM3:
            case R.id.bt_NUM4:
            case R.id.bt_NUM5:
            case R.id.bt_NUM6:
            case R.id.bt_NUM7:
            case R.id.bt_NUM8:
            case R.id.bt_NUM9:
            case R.id.bt_POINT:
                if(clear_flag){
                    clear_flag=false;
                    current="";
                    textview.setText("");
                }//如果flag=true,并且输入一个非操作符号，则重新输入
                textview.setText(current+((Button)v).getText());
                break;
            case R.id.bt_ADD:
            case R.id.bt_MINUS:
            case R.id.bt_MULT:
            case R.id.bt_DIV:
                clear_flag=false;
                textview.setText(current+" "+((Button)v).getText()+" ");
                break;
            case R.id.bt_DEL:
                if(clear_flag){
                    clear_flag=false;
                    textview.setText("");
                }//若flag=true，则删除输出
                else if(current!=null&&!current.equals(""))
                {
                    textview.setText(current.substring(0,current.length()-1));
                }
                break;
            case R.id.bt_AC:
                clear_flag=false;
                current="";
                textview.setText("");
                break;
            case R.id.bt_EQUAL:
                getResult();
                break;

        }


    }
//计算和输出结果
    private void getResult() {
        String cur=textview.getText().toString();
        if(cur==null||cur.equals(""))
        {
            return;
        }
        if(clear_flag){
            clear_flag=false;
            return;
        }
        clear_flag=true;
        //算式分割
        String str1= cur.substring(0,cur.indexOf(" "));
        String str2= cur.substring(cur.indexOf(" ")+1, cur.indexOf(" ")+2);
        String str3=cur.substring(cur.indexOf(" ")+3);

        double result=0;

        if(!str1.equals("")&&!str3.equals(""))
        {
            double num1=Double.parseDouble(str1);
            double num2=Double.parseDouble(str3);

            if(str2.equals("+"))
            {
                result=num1+num2;
            }
            else if(str2.equals("-"))
            {
                result=num1-num2;
            }
            else if(str2.equals("*"))
            {
                result=num1*num2;
            }
            else if(str2.equals("/")) {
                if (num2 == 0) {
                    result = 0;
                } else {
                    result = num1 / num2;
                }
            }
            if(!str1.contains(".") && !str3.contains(".") && !str2.equals("/"))
            {
                int r=(int)result;
                textview.setText(r+"");
            }
            else {
                textview.setText(result+"");
            }
            //textview.setText(result+"");
        }else if (!str1.equals ("") && str3.equals ("")){
            textview.setText (str1);
        }else if(str1.equals("")&&!str3.equals("")){
            if(str2.equals("+"))
                textview.setText(str3);
            else if(str2.equals("-"))
                textview.setText("-"+str3);
            else
                textview.setText("0");
        }
        else
        {
            textview.setText("");
        }
    }
}
