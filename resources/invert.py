from PIL import Image
import PIL.ImageOps
import glob
import sys

def invert(globString):
    filesToInvert = glob.glob(globString)
    for fileString in filesToInvert:
        img = Image.open(fileString).convert('RGBA')
        r, g, b, a = img.split()
        r, g, b = map(PIL.ImageOps.invert, (r, g, b))
        img2 = Image.merge(img.mode, (r, g, b, a))
        img2.save(fileString)
        print("Inverted {}".format(fileString))

if __name__ == "__main__":
    invert(str(sys.argv[1]))
