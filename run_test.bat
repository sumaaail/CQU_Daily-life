python test.py  ^
    --data datasets/coco.data ^
    --cfg cfg/yolov4-tiny.cfg ^
    --weights weights/yolov4-tiny.pt ^
    --img 640 ^
    --iou-thr 0.6 ^
    --conf-thres 0.3 ^
    --batch-size 1