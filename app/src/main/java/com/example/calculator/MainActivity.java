package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

// 主控件
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 点击事件处理
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
            case R.id.btnAdd:
            case R.id.btnSub:
            case R.id.btnMul:
            case R.id.btnDiv:
            case R.id.btnDot: {
                // 将点击的按钮对应的文本附加到表达式中
                Button btn = (Button) view;
                String strAdded = btn.getText().toString();
                TextView formula = (TextView) findViewById(R.id.formula_area);
                String strContent = formula.getText().toString();
                String strNewContent = strContent + strAdded;
                formula.setText(strNewContent);
            }
            break;
            case R.id.btnC: {
                // 清空
                TextView formula = (TextView) findViewById(R.id.formula_area);
                formula.setText("");

                TextView result = (TextView) findViewById(R.id.result_area);
                result.setText("");
            }
            break;
            case R.id.btnDel: {
                // 删除
                TextView formula = (TextView) findViewById(R.id.formula_area);
                String strContent = formula.getText().toString();
                if (strContent.length() > 0) {
                    strContent = strContent.substring(0, strContent.length() - 1);
                    formula.setText(strContent);
                }
            }
            break;
            case R.id.btnEqu: {
                // 计算结果，即 等号

                // 获取表达式，计算结果
                TextView formula = (TextView) findViewById(R.id.formula_area);
                String strContent = formula.getText().toString();

                try {
                    // 调用表达式函数
                    Expression e = new ExpressionBuilder(strContent).build();
                    double res = e.evaluate();

                    TextView result = (TextView) findViewById(R.id.result_area);
                    result.setText(String.valueOf(res));

                    formula.setText("");

                } catch (Throwable e) {
                    Toast.makeText(MainActivity.this, "错误！", Toast.LENGTH_SHORT).show();
                }
            }
            break;

        }
        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
    }
}
