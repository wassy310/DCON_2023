[日本語版 README.md](https://github.com/wassy310/DCON_2023/blob/master/README-ja.md)

# **DCON2023**

## **DCON - National College of Technology Deep Learning Contest**
It stores the programs and other information used in [DCON2023](https://dcon.ai/2023/).  
In addition, image data and other items that could be personal information are excluded from the commitments.

## **My presentation at DCON2023**
### **Presentation theme**
Communication applications for the hearing and speech impaired

### **Overview**
This is a sign language and spoken language interchange application.  
Implemented on a smartphone(Android/iOS) or smart glasses(NrealLight).  
The following mechanism is used to perform mutual conversion between sign language and spoken language.
![structure](https://user-images.githubusercontent.com/74349349/210689422-ecffe937-5d6f-46f9-b4f6-2e5f832b962f.png)
At this time, we are up to the implementation of a simple camera application for Android, and applications for iOS devices and smart glasses are still under development. We plan to release them sequentially in the future.

## **Main software and web services used**
### **VoTT**
- This is a free annotation tool provided by Microsoft that annotates videos and images.
- You can install it from [here](https://github.com/Microsoft/VoTT).
### **Google Colaboratory**
- A service provided by Google Research that allows you to run Python in your browser. The service is suitable for machine learning, data analysis, etc., as it also allows the use of GPU at no cost.
- You can access it from [here](https://colab.research.google.com/).
### **YOLOv5**
- YOLOv5 is a model for object detection and is a very fast processing algorithm.
- I cloned it from [here](https://github.com/ultralytics/yolov5).
### **TensorBoard**
Included with TensorFlow is a dashboard tool called TensorBoard. TensorBoard can visualize data flow graphs, training history (e.g., changes in loss function), or display images and sounds generated in the process.

## **Train**
### **Visualization with TensorBoard**
- White-Line: batch16, epochs100, YOLOv5s
- Orange-Line: batch16, epochs100, YOLOv5x  
<p>
<img src="https://user-images.githubusercontent.com/74349349/211142025-93bebeee-9d46-4f2e-aa13-9d8d95d2eb1c.png" width="420">
<img src="https://user-images.githubusercontent.com/74349349/211142026-b277c491-5b6b-4615-8d62-67d2b8a0d920.png" width="400">
</p>

## **Directory**
```
.
├── datasets (ignored) --------- Dataset used for training
|   ├── images
|   |   ├── train -------------- Image data for learning
|   |   └── val ---------------- Image data for inference
|   ├── labels
|   |   ├── train -------------- Text data for learning
|   |   └── val ---------------- Text data for inference
|   └── data.yaml -------------- File with path and data information needed for training
├── work ----------------------- Directory used to organize and develop image data
|   ├── AndroidCameraApp ------- Android camera application (under dev.)
|   |   └── .
|   └── . (ignored other files (image data, etc.))
|   ├── utils ------------------ Utils to vott2yolo_cv.py
|   |   └── .
|   ├── main.ipynb ------------- Scripts to run deep-learning
|   └── vott2yolo_cv.py -------- Script to convert annotation info output from VoTT for YOLOv5 training
├── yolov5 (ignored) ----------- Cloned from `github.com/ultralytics/yolov5`
|   └── .
├── .gitignore ----------------- This file is used to exclude unnecessary files when committing
├── LICENSE -------------------- Apache License v2.0
└── README.md ------------------ This file contains a description of this repository
```

## **Development environment**
|         |             My PC(Local)           | Google Colaboratory |
|   :-:   | ---------------------------------- | ------------------- |
| OS      | Windows 10 Pro                     | -                   |
| CPU/GPU | Intel Core i5-8250U                | K80 GPU             |
| IDE     | Visual Studio Code, Android Studio | -                   |
| LANG    | Python, Kotlin                     | Python              |

## **Hardware**
|             | Smart Glass                  | Smart Phone             |
|     :-:     | ---------------------------- | ----------------------- |
| Model Name  | NrealLight                   | Xperia Ace              |
| OS          | -                            | Android 9 Pie           |
| SoC         | Qualcomm SnapdragonTM 845    | Qualcomm Snapdragon 630 |
| CPU         | Qualcomm Kryo 385            | ARM Cortex-A53          |
| GPU         | Qualcomm Adreno 630          | Qualcomm Adreno 508     |

## **License**
[Apache License](https://www.apache.org/licenses/LICENSE-2.0)
> ※ YOLOv5 is available under two different licenses:
> - **GPL-3.0 License**: See [LICENSE](https://github.com/ultralytics/yolov5/blob/master/LICENSE) file for details.
> - **Enterprise License**: Provides greater flexibility for commercial product development without the open-source requirements of GPL-3.0. Typical use cases are embedding Ultralytics software and AI models in commercial products and applications. Request an Enterprise License at [Ultralytics Licensing](https://ultralytics.com/license).

## **Thanks**
### **Provider of yolov5**
- [ultralytics, Esq.](https://github.com/ultralytics)
### **Provide conversion scripts for VoTT-YOLOv5 learning**
- [toritamantaro, Esq.](https://github.com/toritamantaro)
### **On-site information provider**
- [Nonprofit organization Ubuntu](http://ubuntu.moon.bindcloud.jp/)
- [Gunma University, School of Common Education](https://www.gunma-u.ac.jp/faculty/facu001/g1813)
- [Gunma Prefectural Tatebayashi Special Support School](https://tatetoku-ses.gsn.ed.jp/)
### **Courtesy of NrealLight**
- [Nreal, Inc.](https://www.nreal.ai/)
