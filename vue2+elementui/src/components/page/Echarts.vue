<template>
    <div>
        <div>
            <el-form :model="dataForm" ref="dataForm" :inline="true">
                <el-form-item label="故障情况" prop="fail">
                    <el-select v-model="dataForm.failName" placeholder="请选择" style="width:  200px;" @change='datachange()'>
                        <el-option v-for="item in options" :key="item" :label="item"
                            :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
        </div>
        <div id="pie" class="chart" style="height:  650px;"></div>
    </div>
</template>
<script>
import Util from "@/assets/js/util";
export default {
    name: "ECharts",
    computed: {
        chartPie() {
            return this.$echarts.init(Util.getDom("pie"));
        }
    },
    data() {
        return {
            options: [],
            dataForm: {
                failName: ''
            },
            fail: [],
            showFail: [],
            showFail2: {
                name: '',
                value: '',
            },
            fail2:[]
        }
    },
    methods: {
        filterArr() {
            for(let i=0;i<this.fail.length;i++){
                this.fail2[i] = this.fail[i].faultType;
            }
            this.options = this.fail2.filter((value, index, self) => {
                return self.indexOf(value) === index;
            });
            console.log(this.fail2);
        },
        resizeChart() {
            window.addEventListener("resize", () => {
                this.chartPie.resize();
            });
        },
        datachange() {
            this.drawPie().resizeChart();
        },
        drawPie() {
            this.showFail = [];
            for (let i = 0; i < this.fail.length; i++) {
                if (this.fail[i].faultType === this.dataForm.failName) {
                    if (this.fail[i].faultPart === null) {
                        this.showFail.push({ name: '未知原因', value: this.fail[i].faultCount })
                    }
                    else {
                        this.showFail.push({ name: this.fail[i].faultPart, value: this.fail[i].faultCount })
                    }
                }
            }
            // console.log(this.fail[0].faultType)
            // console.log(this.dataForm.failName)
            let title = "故障原因分析";
            this.chartPie.setOption({
                title: Object.assign({}, Util.defaultEchartsOpt.title, { text: title }),
                tooltip: {
                    trigger: "item",
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                color: ["#af84cb", "#3acaa9", "#ebcc6f", "#67c4ed", "rgba(32, 254, 255, 0.5)"],
                series: [
                    {
                        name: title,
                        type: "pie",
                        radius: "55%",
                        center: ["50%", "50%"],
                        data: this.showFail,
                        roseType: "radius",
                        label: {
                            normal: {
                                textStyle: {
                                    color: "#777"
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                lineStyle: {
                                    color: "#888"
                                },
                                smooth: 0.2,
                                length: 10,
                                length2: 20
                            }
                        },
                        itemStyle: {
                            normal: {
                                shadowBlur: 100,
                                shadowColor: "rgba(0, 0, 0, 0.5)"
                            }
                        },
                        animationType: "scale",
                        animationEasing: "elasticOut",
                        animationDelay: function (idx) {
                            return Math.random() * 200;
                        }
                    }
                ]
            });
            return this;
        }
    },
    mounted() {
        this.$axios({
            method: 'post',
            url: '/FindAllFaultType'
        }).then(res => {
            this.fail = res.data.message;
            this.dataForm.failName = this.fail[0].faultType;
            this.drawPie().resizeChart();
            this.filterArr();
        }, err => {
            console.log(err);
        });
    }
};
</script>

<style scoped lang="less">
.el-row {
    margin-top: 20px;
}

.chart {
    width: 100%;
    height: 440px;
    .border-radius(8px);
    background-color: @boxBgColor;
    box-shadow: 0 0 5px transparent;

    &:hover {
        box-shadow: 0 0 5px @mainColor;
    }
}
</style>
