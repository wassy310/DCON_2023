[README.md English ver.](https://github.com/wassy310/DCON_2023/blob/master/README.md)

# **DCON2023**

## **DCON - 全国高等専門学校 ディープラーニングコンテスト**
[DCON2023](https://dcon.ai/2023/)で使用したプログラムなどをまとめています。  
また、画像データなど、個人情報になり得るものは除外しています。

## **DCON2023での発表内容**
### **発表テーマ**
聴覚・言語障がい者向け コミュニケーションアプリ

### **概要**
手話と音声言語のコミュニケーションアプリです。  
スマートフォン(Android/iOS)やスマートグラス(NrealLight)に実装します。  
手話と音声言語の相互変換は、以下の仕組みで行います。
![仕組み](https://user-images.githubusercontent.com/74349349/211323811-22d01cc8-b5f4-4d26-97de-9224fc3807a1.png)
現時点では、Android向けの簡易カメラアプリの実装までで、iOSデバイスやスマートグラス向けのアプリは開発途中です。今後、順次リリース予定です。

## **使用した主なソフトウェアやwebサービス**
### **VoTT**
- マイクロソフトが提供する、動画や画像にタグをつけるための無料のアノテーションツールです。
- [ここ](https://github.com/Microsoft/VoTT)からインストールできます。
### **Google Colaboratory**
- Google Researchが提供する、ブラウザ上でPythonを実行できるサービスです。GPUも無償で利用できるため、機械学習やデータ解析などに適したサービスです。
- [ここ](https://colab.research.google.com/)からアクセスできます。
### **YOLOv5**
- YOLOv5は物体検出のモデルで、非常に高速に処理できるアルゴリズムです。
- [ここ](https://github.com/ultralytics/yolov5)からクローンして使用しました。
### **TensorBoard**
TensorFlowには、TensorBoardというダッシュボードツールが付属しています。TensorBoardは、データフローのグラフや学習履歴（損失関数の変化など）を可視化したり、処理中に発生した画像や音声を表示したりすることができます。

## **学習**
### **TensorBoardで可視化**
- 白線: batch16, epochs100, YOLOv5s
- 橙線: batch16, epochs100, YOLOv5x  
<p>
<img src="https://user-images.githubusercontent.com/74349349/211142025-93bebeee-9d46-4f2e-aa13-9d8d95d2eb1c.png" width="420">
<img src="https://user-images.githubusercontent.com/74349349/211142026-b277c491-5b6b-4615-8d62-67d2b8a0d920.png" width="400">
</p>

## **ディレクトリ構成**
```
.
├── datasets (ignored) --------- 学習用のデータセット
|   ├── images
|   |   ├── train -------------- 学習用画像データ
|   |   └── val ---------------- 推論用画像データ
|   ├── labels
|   |   ├── train -------------- 学習用テキストデータ
|   |   └── val ---------------- 推論用テキストデータ
|   └── data.yaml -------------- トレーニングに必要なパスやデータ情報を記載したファイル
├── work ----------------------- 画像データの整理・開発に使用したディレクトリ
|   ├── AndroidCameraApp ------- Androidカメラアプリ (開発中)
|   |   └── .
|   └── . (.gitignoreで無効化した他のファイル(画像ファイルなど))
|   ├── utils ------------------ vott2yolo_cv.py のutil
|   |   └── .
|   ├── main.ipynb ------------- 学習させる際に使用したメインプログラム
|   └── vott2yolo_cv.py -------- VoTTから出力されるアノテーション情報をYOLOv5学習用に変換するスクリプト
├── yolov5 (ignored) ----------- `github.com/ultralytics/yolov5`からクローン
|   └── .
├── .gitignore ----------------- コミット時に不要なファイルを除外するため
├── LICENSE -------------------- Apache License v2.0
└── README.md ------------------ このリポジトリの説明
```

## **開発環境**
|         |          自分のPC(ローカル)         | Google Colaboratory |
|   :-:   | ---------------------------------- | ------------------- |
| OS      | Windows 10 Pro                     | -                   |
| CPU/GPU | Intel Core i5-8250U                | K80 GPU             |
| IDE     | Visual Studio Code, Android Studio | -                   |
| LANG    | Python, Kotlin                     | Python              |

## **ハードウェア**
|             | スマートグラス                | スマートフォン            |
|     :-:     | ---------------------------- | ----------------------- |
| モデル名     | NrealLight                   | Xperia Ace              |
| OS          | -                            | Android 9 Pie           |
| SoC         | Qualcomm SnapdragonTM 845    | Qualcomm Snapdragon 630 |
| CPU         | Qualcomm Kryo 385            | ARM Cortex-A53          |
| GPU         | Qualcomm Adreno 630          | Qualcomm Adreno 508     |

## **ライセンス**
[Apache License](https://www.apache.org/licenses/LICENSE-2.0)
> ※ YOLOv5は、2種類のライセンスで提供されます:
> - **GPL-3.0 License**: See [LICENSE](https://github.com/ultralytics/yolov5/blob/master/LICENSE) file for details.
> - **Enterprise License**: Provides greater flexibility for commercial product development without the open-source requirements of GPL-3.0. Typical use cases are embedding Ultralytics software and AI models in commercial products and applications. Request an Enterprise License at [Ultralytics Licensing](https://ultralytics.com/license).

## **深謝**
### **yolov5の提供者**
- [ultralytics 様](https://github.com/ultralytics)
### **VoTT-YOLOv5学習用変換スクリプトの提供者**
- [toritamantaro 様](https://github.com/toritamantaro)
### **現場の情報提供者(敬称略)**
- [NPO法人 Ubuntu](http://ubuntu.moon.bindcloud.jp/)
- [群馬大学 共同教育学部](https://www.gunma-u.ac.jp/faculty/facu001/g1813)
- [群馬県立館林特別支援学校](https://tatetoku-ses.gsn.ed.jp/)
### **NrealLightの提供**
- [Nreal社 様](https://www.nreal.ai/)
